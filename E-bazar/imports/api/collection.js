import { Mongo } from 'meteor/mongo';

export const userdb = new Mongo.Collection('userdb');
export const itemdb = new Mongo.Collection('itemdb');