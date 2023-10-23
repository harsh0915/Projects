import { Template } from 'meteor/templating';
import { Meteor } from 'meteor/meteor'
import './../../routes/routes';


Template.login.events({
    'submit #loginform': async (e) => {
        e.preventDefault();
        let email = e.target.email.value;
        let password = e.target.password.value;
        const result = await new Promise(function (resolve, reject) {
            return Meteor.call('checkUser', email, password, (err, result) => {
                if (err)
                    reject('ERROR');
                else
                    resolve(result)
            });
        });
        if (result) {
            sessionStorage.setItem('user', email);
            Router.go('/chatroom')
        }
        else {
            alert('Oops! wrong username or password. Please try again.')
            e.target.password.value = "";
            e.target.email.value = "";
        }

    }
})