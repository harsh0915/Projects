import './../imports/ui/components/btnpanel.html';
import './../imports/ui/components/login.html';
import './../imports/ui/components/signup.html';
import './../imports/ui/pages/home.html';
import './../imports/ui/layouts/layout.html'

Router.configure({
    layoutTemplate: 'layout'
});

Router.route('/',
    function () {
        this.render('btnpanel')
    });

Router.route('/login',
    function () {
        this.render('login');
    });

Router.route('/signup',
    function () {
        this.render('signup')
    });

Router.route('/home',
    function () {
        this.render('home');
    });


