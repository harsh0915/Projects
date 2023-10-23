import { Template } from 'meteor/templating';
import { Meteor } from 'meteor/meteor';
import './../../routes/routes';

Template.wrtbox.events({
    'submit #wrtbox': function (e) {
        e.preventDefault();
        var msg = e.target.msg.value;
        var user = sessionStorage.getItem('user');
        if (msg != '')
            Meteor.call('send', msg, user);
        else
            alert(`Message can't be empty!`);

        e.target.msg.value = "";
    }
})