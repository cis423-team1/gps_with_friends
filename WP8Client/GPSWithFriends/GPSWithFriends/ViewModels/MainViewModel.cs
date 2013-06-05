using System;
using System.Collections.ObjectModel;
using System.ComponentModel;
using GPSWithFriends.Resources;

namespace GPSWithFriends.ViewModels
{
    public class MainViewModel : INotifyPropertyChanged
    {
        public MainViewModel()
        {
            this.Friends = new ObservableCollection<Friend>();
            this.Requests = new ObservableCollection<Request>();
        }

        /// <summary>
        /// A collection for ItemViewModel objects.
        /// </summary>
        public ObservableCollection<Friend> Friends { get; private set; }
        public ObservableCollection<Request> Requests { get; private set; }

        private Friend currentFriend;
        public Friend CurrentFriend
        {
            get
            {
                return currentFriend;
            }
            set
            {
                if (value != currentFriend)
                {
                    currentFriend = value;
                    NotifyPropertyChanged("CurrentFriend");
                }
            }
        }

        private string _sampleProperty = "Sample Runtime Property Value";
        /// <summary>
        /// Sample ViewModel property; this property is used in the view to display its value using a Binding
        /// </summary>
        /// <returns></returns>
        public string SampleProperty
        {
            get
            {
                return _sampleProperty;
            }
            set
            {
                if (value != _sampleProperty)
                {
                    _sampleProperty = value;
                    NotifyPropertyChanged("SampleProperty");
                }
            }
        }

        /// <summary>
        /// Sample property that returns a localized string
        /// </summary>
        public string LocalizedSampleProperty
        {
            get
            {
                return AppResources.SampleProperty;
            }
        }

        public bool IsDataLoaded
        {
            get;
            private set;
        }

        /// <summary>
        /// Creates and adds a few ItemViewModel objects into the Items collection.
        /// </summary>
        public void LoadData()
        {
            // Sample data; replace with real data
            this.Friends.Add(new Friend() { NickName = "Wang Cong", Status = "updated in 16:20", Distance = "1.5 km", ImagePath = "/Assets/fakePor.png", Email = "Jushua@gmail.com", Latitude = 39.7677, Longitude = 116.3602});
            this.Friends.Add(new Friend() { NickName = "Yu Zhe", Status = "updated in 16:22", Distance = "1 km", ImagePath = "/Assets/fakePor.png", Email = "Kate@gmail.com", Latitude = 39.7588, Longitude = 116.3510 });
            this.Friends.Add(new Friend() { NickName = "Kate.Xu", Status = "updated in 15:30", Distance = "1.3 km", ImagePath = "/Assets/fakePor.png", Email = "Bao@gmail.com", Latitude = 39.7532, Longitude = 116.3452 });
            this.Friends.Add(new Friend() { NickName = "Rye", Status = "updated in 16:12", Distance = "3 km", ImagePath = "/Assets/fakePor.png", Email = "Stranger@gmail.com", Latitude = 39.7532, Longitude = 116.3602 });

            this.Requests.Add(new Request() { Content = "Yu wants to friend you", Time="8/5/2013 13:04",SenderName="Yu",SenderEmail="Yu@163.com" });
            this.Requests.Add(new Request() { Content = "Hongye wants to friend you", Time = "7/5/2013 12:44", SenderName = "Hongye", SenderEmail = "Hongye@163.com" });
            this.Requests.Add(new Request() { Content = "Kevin wants to friend you", Time = "7/5/2013 10:21", SenderName = "Kevin", SenderEmail = "Kevin@163.com" });

            this.IsDataLoaded = true;
        }

        public event PropertyChangedEventHandler PropertyChanged;
        private void NotifyPropertyChanged(String propertyName)
        {
            PropertyChangedEventHandler handler = PropertyChanged;
            if (null != handler)
            {
                handler(this, new PropertyChangedEventArgs(propertyName));
            }
        }
    }
}