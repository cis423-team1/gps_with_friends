using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Navigation;
using Microsoft.Phone.Controls;
using Microsoft.Phone.Shell;
using System.IO.IsolatedStorage;

namespace GPSWithFriends
{
    public partial class LoginPage : PhoneApplicationPage
    {
        private IsolatedStorageSettings _appSettings = IsolatedStorageSettings.ApplicationSettings;

        Server.GPSwfriendsClient proxy = new Server.GPSwfriendsClient();

        public LoginPage()
        {
            InitializeComponent();
            ReadLastLoginUser();
        }
       
        protected override void OnNavigatedTo(NavigationEventArgs e)
        {            
            this.NavigationService.RemoveBackEntry();
            ReadLastLoginUser();
        }


        private void LOGINBUTTON_Click(object sender, RoutedEventArgs e)
        {
            proxy.authenticateCompleted += proxy_authenticateCompleted;
            proxy.authenticateAsync(LoginUsernameTextBox.Text, LoginPasswordBox.Password);
            LOGINBUTTON.IsEnabled = false;
            REGISTERBUTTON.IsEnabled = false;
        }

        private void proxy_authenticateCompleted(object sender, Server.authenticateCompletedEventArgs e)
        {
            if (e.Result.success)
            {
                SaveLastLoginUser(LoginUsernameTextBox.Text);
                this.NavigationService.Navigate(new Uri("/MainPage.xaml", UriKind.Relative));
            }
            else
            {
                MessageBox.Show("Login Failed. Please try again.");
                LoginPasswordBox.Password = "";
                LoginPasswordBox.Focus();
            }
            LOGINBUTTON.IsEnabled = true;
            REGISTERBUTTON.IsEnabled = true;
        }


        private void ReadLastLoginUser()
        {
            //READ LAST LOGIN USER NAME
            string tempLastLoginUserName;

            if (_appSettings.Count > 0 && _appSettings.Contains("LAST_LOGIN_USERNAME"))
            {
                if (_appSettings.TryGetValue<string>("LAST_LOGIN_USERNAME", out tempLastLoginUserName))
                    LoginUsernameTextBox.Text = tempLastLoginUserName;
                else LoginUsernameTextBox.Text = "";
            }
            else
                LoginUsernameTextBox.Text = "";
        }

        private void SaveLastLoginUser(string lastLoginUser)
        {
            if (_appSettings.Contains("LAST_LOGIN_USERNAME"))
                _appSettings["LAST_LOGIN_USERNAME"] = lastLoginUser;
            else
                _appSettings.Add("LAST_LOGIN_USERNAME", lastLoginUser);
        }

        private void REGISTERBUTTON_Click(object sender, RoutedEventArgs e)
        {
            ///Test code
            //string email="bunny_gg@hotmail.com";
            //string password = "test";
            //string fname="yingnan";
            //string lname = "ju";
            //Server.GPSwfriendsClient proxy = new Server.GPSwfriendsClient();
            //proxy.registerCompleted += proxy_registerCompleted;
            //proxy.registerAsync(email, password, fname, lname);
            this.NavigationService.Navigate(new Uri("/RegisterPage.xaml", UriKind.Relative));
        }

        void proxy_registerCompleted(object sender, Server.registerCompletedEventArgs e)
        {
            Server.status s = e.Result;
            REGISTERBUTTON.Content = s.ToString();
        }

        private void LoginPasswordBox_Loaded(object sender, RoutedEventArgs e)
        {
            //this.NavigationService.RemoveBackEntry();
        }
    }
}