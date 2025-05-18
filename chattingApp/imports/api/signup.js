import { Template } from 'meteor/templating';
import { Meteor } from 'meteor/meteor'
import './../../routes/routes';

// Meteor.subscribe('username');
Template.signup.events({
    'submit #signupform': async (e) => {

        e.preventDefault();
        let username = e.target.username.value;
        let email = e.target.email.value;
        let password = e.target.password.value;
        let confirmpassword = e.target.confirmpassword.value;

        //register user in the database
        const result = await new Promise((resolve, reject) => Meteor.call('duplicacyCheck', email, (err, res) => {
            if (err)
                reject(err);
            else
                resolve(res);
        }));

        if (password != confirmpassword) {
            alert('The passwords dont match!');
            e.target.password.value = "";
            e.target.confirmpassword.value = "";
        }
        else if (result) {
            alert(`This email is already in use!`);
        }
        else {

            Meteor.call('addUser', username, email, password);
            e.target.username.value = '';
            e.target.email.value = '';
            e.target.password.value = '';
            e.target.confirmpassword.value = '';
            alert('User signup successfull!');
            Router.go('/login')
        }
    }
});

