import { msgdb } from '../imports/collections/msgs';
import { Meteor } from 'meteor/meteor';

Meteor.methods({

    send: function (msg, user) {
        var time = new Date();
        msgdb.insert({
            User: user,
            Message: msg,
            Date: time.toLocaleTimeString(),
        })
    },

})