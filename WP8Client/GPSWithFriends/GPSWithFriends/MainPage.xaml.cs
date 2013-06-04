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
using Microsoft.Phone.Maps.Toolkit;

namespace GPSWithFriends
{
    public partial class MainPage : PhoneApplicationPage
    {
        //GPS
        Geolocator myGeoLocator = new Geolocator();
        Friend Me = new Friend() { Latitude = -1, Longitude = -1, NickName="Me"};
        UserLocationMarker myLocationMarker = new UserLocationMarker();
        UserLocationMarker[] userLocationMarker;
        RouteQuery MyQuery = null;
        MapRoute MyMapRoute = null;
        List<GeoCoordinate> MyCoordinates = new List<GeoCoordinate>();
        
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

            userLocationMarker = new UserLocationMarker[App.ViewModel.Friends.Count];
            for (int i = 0; i < userLocationMarker.Length; i++)
            {
                userLocationMarker[i] = new UserLocationMarker();
            }

            await LocateMe();
            foreach (var item in App.ViewModel.Friends)
            {
                if(item.isLocated())
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
                {
                    FindSomeone(Me);
                    MyMap.SetView(new GeoCoordinate(Me.Latitude, Me.Longitude), MyMap.ZoomLevel, MapAnimationKind.Parabolic);
                }
                //LocateSomeone(new Friend() { Latitude = Me.Latitude + 0.01, Longitude = Me.Longitude + 0.01, NickName = "TEST" });
            }
        }

        private void FindSomeone(Friend friend)
        {
            GPSLocationTextblock.Text = "Location: ";
            string positionString = string.Format("Lat: {0:0.0000}, Long: {1:0.0000}",
                 friend.Latitude, friend.Longitude);
            GPSLocationTextblock.Text += positionString;

            //MyMap.SetView(new GeoCoordinate(friend.Latitude, friend.Longitude), MyMap.ZoomLevel, MapAnimationKind.Parabolic);

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
            myLocationOverlay.GeoCoordinate = new GeoCoordinate(friend.Latitude, friend.Longitude);

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
            if (index > -1 && index < App.ViewModel.Friends.Count)
            {
                Friend friend = App.ViewModel.Friends[index];
                if (friend.isLocated())
                {
                    MyMap.SetView(new GeoCoordinate(friend.Latitude, friend.Longitude), MyMap.ZoomLevel, MapAnimationKind.Parabolic);
                    //MyMap.Center = new GeoCoordinate(friend.Latitude, friend.Longitude);
                }
            }
        }

        private void InviteButton_Click(object sender, RoutedEventArgs e)
        {
            InputFriendEmail();
        }

        private void InputFriendEmail()
        {
            TextBox emailInputBox = new TextBox()
            {
                //InputScope=System.Windows.Input.InputScopeNameValue.EmailSmtpAddress
            };
            TiltEffect.SetIsTiltEnabled(emailInputBox, true);
            CustomMessageBox messageBox = new CustomMessageBox()
            {
                Caption = "Please input Email Address:",
                Message = "Please input email address of the friend that you want to add.",
                Content = emailInputBox,
                LeftButtonContent = "Add",
                RightButtonContent = "Cancel",
                IsFullScreen = false,
            };

            messageBox.Dismissed += (s1, e1) =>
            {
                switch (e1.Result)
                {
                    case CustomMessageBoxResult.LeftButton:
                        string result = "";
                        result = emailInputBox.Text;
                        if (result.Length > 0)
                            SendFriendRequest(result);
                        break;
                    case CustomMessageBoxResult.RightButton:
                        // Do something.
                        break;
                    case CustomMessageBoxResult.None:
                        // Do something.
                        break;
                    default:
                        break;
                }
            };

            messageBox.Show();
        }

        public static void SendFriendRequest(string result)
        {
            //throw new NotImplementedException();
        }

        private void RequestsListBox_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            ListBox listbox = sender as ListBox;
            int index = listbox.SelectedIndex;            
            if (index > -1 && index < App.ViewModel.Requests.Count)
            {
                Request request = App.ViewModel.Requests[index];
                RequestHandle(request);
            }
        }

        private void RequestHandle(Request request)
        {
            CustomMessageBox messageBox = new CustomMessageBox()
            {
                Caption = "Request Accept?",
                Message = request.Content,
                LeftButtonContent = "Accept",
                RightButtonContent = "Reject",
                IsFullScreen = false,
            };

            messageBox.Dismissed += (s1, e1) =>
            {
                switch (e1.Result)
                {
                    case CustomMessageBoxResult.LeftButton:
                        RequestDone(request, true);
                        break;
                    case CustomMessageBoxResult.RightButton:
                        RequestDone(request, false);
                        break;
                    case CustomMessageBoxResult.None:
                        // Do something.
                        break;
                    default:
                        break;
                }
            };

            messageBox.Show();
        }

        private void RequestDone(Request request, bool p)
        {
            //1. send request handle info to cloud
            App.ViewModel.Requests.Remove(request);
            if (p)
            {
                //2. get friend from cloud
                //3. add friend

                //temp
                App.ViewModel.Friends.Add(new Friend
                {
                    IsFriend = true,
                    Email = request.SenderEmail,
                    NickName = request.SenderName,
                    Distance="???",
                    ImagePath = "/Assets/fakePor.png"
                });
                //throw new NotImplementedException();
            }
        }

        private void FriendsManageListBox_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            ListBox listbox = sender as ListBox;
            int index = listbox.SelectedIndex;
            if (index > -1 && index < App.ViewModel.Friends.Count)
            {
                App.ViewModel.CurrentFriend = App.ViewModel.Friends[index];
                this.NavigationService.Navigate(new Uri("/DetailPage.xaml", UriKind.Relative));
            }
        }

        private void FriendList_Refresh(object sender, RoutedEventArgs e)
        {
            MyMap.Layers.Clear();
            App.ViewModel.Friends[0].Longitude += 0.01;
            foreach (var item in App.ViewModel.Friends)
            {
                if (item.isLocated())
                    FindSomeone(item);
            }
        }

        private void FriendList_Route(object sender, RoutedEventArgs e)
        {
            try
            {
                int selectedIndex = App.ViewModel.Friends.IndexOf((sender as MenuItem).DataContext as Friend);
                MyQuery = new RouteQuery();
                Friend friend = App.ViewModel.Friends[selectedIndex];
                //MyCoordinates.Add(e.Result[0].GeoCoordinate);
                MyCoordinates.Add(new GeoCoordinate(Me.Latitude, Me.Longitude));
                MyCoordinates.Add(new GeoCoordinate(friend.Latitude, friend.Longitude));
                MyQuery.Waypoints = MyCoordinates;
                MyQuery.QueryCompleted += MyQuery_QueryCompleted;
                MyQuery.QueryAsync();
                //MyMap.SetView(new LocationRectangle(new GeoCoordinate(Me.Latitude, Me.Longitude),new GeoCoordinate(friend.Latitude, friend.Longitude)));
            }
            catch (Exception)
            {
                MessageBox.Show("Something gotta wrong.");
            }
        }

        private void MyQuery_QueryCompleted(object sender, QueryCompletedEventArgs<Route> e)
        {
            if (e.Error == null)
            {
                Route MyRoute = e.Result;
                if (MyMapRoute != null)
                {
                    MyMap.RemoveRoute(MyMapRoute);
                }
                MyMap.Layers.Clear();
                MyMapRoute = new MapRoute(MyRoute);
                MyMap.AddRoute(MyMapRoute);                
                MyQuery.Dispose();
            }
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