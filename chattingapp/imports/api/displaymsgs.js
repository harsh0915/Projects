import { Template } from 'meteor/templating'
import { Meteor } from 'meteor/meteor'
import { msgdb } from './../collections/msgs'
import './../../routes/routes';


Template.msgcont.helpers({
    'display': function () {
        Meteor.subscribe('msgs');
        return msgdb.find();
    }
});