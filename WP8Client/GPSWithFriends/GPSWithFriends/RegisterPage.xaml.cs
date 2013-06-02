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
    public partial class RegisterPage : PhoneApplicationPage
    {
        private IsolatedStorageSettings _appSettings = IsolatedStorageSettings.ApplicationSettings;

        public RegisterPage()
        {
            InitializeComponent();
        }

        private void Submit_Button_Click(object sender, RoutedEventArgs e)
        {
            if (ContentCheck())
            {
                if (Register(RegisterEmailTextBox.Text, RegisterPasswordBox.Password, RegisterNickNameTextBox.Text))
                {
                    SaveLastLoginUser(RegisterEmailTextBox.Text);
                    this.NavigationService.Navigate(new Uri("/LoginPage.xaml", UriKind.Relative));
                }
                else
                    MessageBox.Show("Register failed. Please try again.");
            }
            else
                MessageBox.Show("Input incomplete or password not match. Please try again.");
        }

        private void SaveLastLoginUser(string lastLoginUser)
        {
            if (_appSettings.Contains("LAST_LOGIN_USERNAME"))
                _appSettings["LAST_LOGIN_USERNAME"] = lastLoginUser;
            else
                _appSettings.Add("LAST_LOGIN_USERNAME", lastLoginUser);
        }


        private bool Register(string p1, string p2, string p3)
        {
            return true;
        }

        private bool ContentCheck()
        {
            if (RegisterEmailTextBox.Text.Length > 0 &&
                RegisterNickNameTextBox.Text.Length > 0 &&
                RegisterPasswordBox.Password.Length > 0 &&
                RegisterPasswordBox.Password.Equals(RegisterPasswordAgainBox.Password))
                return true;
            else
                return false;
        }
    }
}