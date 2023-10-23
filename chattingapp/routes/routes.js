import './../imports/ui/components/login'
import './../imports/ui/components/landing'
import './../imports/ui/components/signup'
import './../imports/ui/layout/signuplogin'
import './../imports/ui/layout/chatlayout'
import './../imports/ui/components/wrtbox'
import './../imports/ui/components/msgcont'



Router.route('/',
    function () {
        this.render('landing'),
            this.layout('signuplogin')
    });

Router.route('/login',
    function () {
        this.render('login'),
            this.layout('signuplogin')
    });

Router.route('/signup',
    function () {
        this.render('signup'),
            this.layout('signuplogin')
    });

Router.route('/chatroom',
    function () {
        this.render('msgcont', { to: '1' }),
            this.render('wrtbox', { to: '2' }),
            this.layout('chatlayout')
    });