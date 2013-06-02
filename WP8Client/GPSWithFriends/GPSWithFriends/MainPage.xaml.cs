using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Navigation;
using Microsoft.Phone.Controls;
using Microsoft.Phone.Shell;
using GPSWithFriends.Resources;
using Windows.Devices.Geolocation;
using Microsoft.Phone.Tasks;
using System.Device.Location;
using System.Windows.Shapes;
using System.Windows.Media;
using Microsoft.Phone.Maps.Controls;
using GPSWithFriends.ViewModels;
using Microsoft.Phone.Maps.Services;

namespace GPSWithFriends
{
    public partial class MainPage : PhoneApplicationPage
    {
        //GPS
        Geolocator myGeoLocator = new Geolocator();
        Friend Me = new Friend() { Latitude = -1, Longitude = -1, NickName="Me"};
        RouteQuery MyQuery = null;
        GeocodeQuery Mygeocodequery = null;

        // Constructor
        public MainPage()
        {
            InitializeComponent();

            // Set the data context of the listbox control to the sample data
            DataContext = App.ViewModel;

            // Sample code to localize the ApplicationBar
            //BuildLocalizedApplicationBar();

            myGeoLocator.DesiredAccuracy = PositionAccuracy.High;
            myGeoLocator.MovementThreshold = 50;
        }

        // Load data for the ViewModel Items
        protected override async void OnNavigatedTo(NavigationEventArgs e)
        {
            if (!App.ViewModel.IsDataLoaded)
            {
                App.ViewModel.LoadData();
            }
            while (this.NavigationService.CanGoBack)
            {
                this.NavigationService.RemoveBackEntry();
            }

            await LocateMe();
            foreach (var item in App.ViewModel.Friends)
            {
                FindSomeone(item);
            }
        }

        private string GetCoordinateString(Geocoordinate geocoordinate)
        {
            string positionString = string.Format("Lat: {0:0.0000}, Long: {1:0.0000}, Acc: {2}m",
                 geocoordinate.Latitude, geocoordinate.Longitude, geocoordinate.Accuracy);
            Me.Latitude = geocoordinate.Latitude;
            Me.Longitude = geocoordinate.Longitude;
            return positionString;
        }

        private void PhoneApplicationPage_Loaded(object sender, RoutedEventArgs e)
        {
            //this.NavigationService.RemoveBackEntry();
        }

        private async void ApplicationBarIconButton_Click(object sender, EventArgs e)
        {
            //BingMapsTask _tskBingmap = new BingMapsTask();
            await LocateMe();
        }

        private async System.Threading.Tasks.Task LocateMe()
        {
            try
            {
                Geoposition position = await myGeoLocator.GetGeopositionAsync(maximumAge: TimeSpan.FromMinutes(1), timeout: TimeSpan.FromSeconds(30));
                GPSLocationTextblock.Text = "Location: ";
                GPSLocationTextblock.Text += this.GetCoordinateString(position.Coordinate);
            }
            catch (UnauthorizedAccessException)
            {
                GPSLocationTextblock.Text = "Location is disabled in phone settings.";
            }
            catch (Exception ex)
            {
                GPSLocationTextblock.Text = ex.Message;
            }
            finally
            {
                if (Me.isLocated())
                    FindSomeone(Me);
                //LocateSomeone(new Friend() { Latitude = Me.Latitude + 0.01, Longitude = Me.Longitude + 0.01, NickName = "TEST" });
            }
        }

        private void FindSomeone(Friend friend)
        {
            GPSLocationTextblock.Text = "Location: ";
            string positionString = string.Format("Lat: {0:0.0000}, Long: {1:0.0000}",
                 friend.Latitude, friend.Longitude);
            GPSLocationTextblock.Text += positionString;

            MyMap.Center = new GeoCoordinate(friend.Latitude, friend.Longitude);

            // Create a small circle to mark the current location.
            Ellipse myCircle = new Ellipse();
            myCircle.Stroke = new SolidColorBrush(Colors.Black);
            myCircle.StrokeThickness = 5;
            myCircle.Fill = new SolidColorBrush((Color)Application.Current.Resources["PhoneAccentColor"]);
            myCircle.Height = 20;
            myCircle.Width = 20;
            myCircle.Opacity = 50;

            StackPanel stackpanel = new StackPanel();
            stackpanel.Children.Add(myCircle);
            TextBlock name = new TextBlock();
            name.Text = friend.NickName;
            stackpanel.Children.Add(name);

            // Create a MapOverlay to contain the circle.
            MapOverlay myLocationOverlay = new MapOverlay();
            myLocationOverlay.Content = stackpanel;
            myLocationOverlay.PositionOrigin = new Point(0.5, 0.5);
            myLocationOverlay.GeoCoordinate = MyMap.Center;

            // Create a MapLayer to contain the MapOverlay.
            MapLayer myLocationLayer = new MapLayer();
            myLocationLayer.Add(myLocationOverlay);

            // Add the MapLayer to the Map.
            MyMap.Layers.Add(myLocationLayer);
        }

        private void FriendsListBox_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            ListBox listbox = sender as ListBox;
            int index = listbox.SelectedIndex;
            Friend friend = App.ViewModel.Friends[index];
            MyMap.Center = new GeoCoordinate(friend.Latitude, friend.Longitude);
        }

        // Sample code for building a localized ApplicationBar
        //private void BuildLocalizedApplicationBar()
        //{
        //    // Set the page's ApplicationBar to a new instance of ApplicationBar.
        //    ApplicationBar = new ApplicationBar();

        //    // Create a new button and set the text value to the localized string from AppResources.
        //    ApplicationBarIconButton appBarButton = new ApplicationBarIconButton(new Uri("/Assets/AppBar/appbar.add.rest.png", UriKind.Relative));
        //    appBarButton.Text = AppResources.AppBarButtonText;
        //    ApplicationBar.Buttons.Add(appBarButton);

        //    // Create a new menu item with the localized string from AppResources.
        //    ApplicationBarMenuItem appBarMenuItem = new ApplicationBarMenuItem(AppResources.AppBarMenuItemText);
        //    ApplicationBar.MenuItems.Add(appBarMenuItem);
        //}
    }
}