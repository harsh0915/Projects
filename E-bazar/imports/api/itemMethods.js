import { Meteor } from 'meteor/meteor';
import { itemdb } from './collection';
import SimpleSchema from 'simpl-schema';

const fs = require('fs');

itemdb.schema = new SimpleSchema({
    itemName: { type: String, required: true },
    itemCost: { type: Number, required: true },
    itemDescription: { type: String, optional: true }
});

Meteor.methods({
    sendItems: () => {
        return itemdb.find({})
    }
})

