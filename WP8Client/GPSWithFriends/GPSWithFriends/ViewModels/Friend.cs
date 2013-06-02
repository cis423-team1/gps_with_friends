using System;
using System.ComponentModel;
using System.Diagnostics;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;

namespace GPSWithFriends.ViewModels
{
    public class Friend : INotifyPropertyChanged
    {
       public Friend()
        {
            nickName = "";
            status = "";
            distance = "";
            email = "";
            isFriend = false;
            imagePath = "";
            latitude = 181;
            longitude = 181;
        }

       public bool isLocated()
       {
           if (latitude < 180 && longitude < 180 && latitude > -180 && longitude > -180)
               return true;
           else return false;
       }

        private string nickName;
        public string NickName
        {
            get
            {
                return nickName;
            }
            set
            {
                if (value != nickName)
                {
                    nickName = value;
                    NotifyPropertyChanged("NickName");
                }
            }
        }

        private string status;
        public string Status
        {
            get
            {
                return status;
            }
            set
            {
                if (value != status)
                {
                    status = value;
                    NotifyPropertyChanged("Status");
                }
            }
        }

        private string distance;
        public string Distance
        {
            get
            {
                return distance;
            }
            set
            {
                if (value != distance)
                {
                    distance = value;
                    NotifyPropertyChanged("Distance");
                }
            }
        }

        private string email;
        public string Email
        {
            get
            {
                return email;
            }
            set
            {
                if (value != email)
                {
                    email = value;
                    NotifyPropertyChanged("Email");
                }
            }
        }

        private bool isFriend;
        public bool IsFriend
        {
            get
            {
                return isFriend;
            }
            set
            {
                if (value != isFriend)
                {
                    isFriend = value;
                    NotifyPropertyChanged("IsFriend");
                }
            }
        }

        private string imagePath;
        public string ImagePath
        {
            get
            {
                return imagePath;
            }
            set
            {
                if (value != imagePath)
                {
                    imagePath = value;
                    NotifyPropertyChanged("ImagePath");
                }
            }
        }

        private double latitude;
        public double Latitude
        {
            get
            {
                return latitude;
            }
            set
            {
                if (value != latitude)
                {
                    latitude = value;
                    NotifyPropertyChanged("Latitude");
                }
            }
        }

        private double longitude;
        public double Longitude
        {
            get
            {
                return longitude;
            }
            set
            {
                if (value != longitude)
                {
                    longitude = value;
                    NotifyPropertyChanged("Longitude");
                }
            }
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