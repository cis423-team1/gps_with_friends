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
    public partial class RegisterPage : PhoneApplicationPage
    {
        public RegisterPage()
        {
            InitializeComponent();
        }

        private void Submit_Button_Click(object sender, RoutedEventArgs e)
        {
            this.NavigationService.Navigate(new Uri("/LoginPage.xaml", UriKind.Relative));
        }
    }
}