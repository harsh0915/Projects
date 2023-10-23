import { Meteor } from 'meteor/meteor';
import { userdb } from './collection';

Meteor.methods({

    checkUser: (enteredEmail, enteredPassword) => {
        if (userdb.findOne({ email: enteredEmail }) && userdb.findOne({ password: enteredPassword }))
            return true;
        else
            return false;
    },

    addUser: (enteredName, enteredEmail, enteredPassword) => {
        userdb.insert({
            username: enteredName,
            email: enteredEmail,
            password: enteredPassword,
        })
    },

    delUser: (id) => {
        userdb.remove({ _id: id });
    },

    duplicacyCheck: (enteredEmail) => {
        if (userdb.findOne({ email: enteredEmail }) == null)
            return false;
        else
            return true;
    }

})

