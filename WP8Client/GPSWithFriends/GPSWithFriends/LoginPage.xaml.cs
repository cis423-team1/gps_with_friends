using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Navigation;
using Microsoft.Phone.Controls;
using Microsoft.Phone.Shell;

namespace GPSWithFriends
{
    public partial class LoginPage : PhoneApplicationPage
    {
        public LoginPage()
        {
            InitializeComponent();
        }

        private void LOGINBUTTON_Click(object sender, RoutedEventArgs e)
        {            
            this.NavigationService.Navigate(new Uri("/MainPage.xaml",UriKind.Relative));
        }

        private void REGISTERBUTTON_Click(object sender, RoutedEventArgs e)
        {
            string email="bunny_gg@hotmail.com";
            string password = "test";
            string fname="yingnan";
            string lname = "ju";
            Server.GPSwfriendsClient proxy = new Server.GPSwfriendsClient();
            proxy.registerCompleted += proxy_registerCompleted;
            proxy.registerAsync(email, password, fname, lname);
        }

       void proxy_registerCompleted(object sender, Server.registerCompletedEventArgs e)
        {
            Server.status s = e.Result;
            REGISTERBUTTON.Content = s.ToString();
        }
    }
}