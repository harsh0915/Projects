import { msgdb } from '../imports/collections/msgs';
import { Meteor } from 'meteor/meteor';

Meteor.publish('msgs', function () {
    return msgdb.find({});
})
