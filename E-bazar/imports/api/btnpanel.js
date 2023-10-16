import { Template } from 'meteor/templating';
import './../../client/main.html';
import '../../routes/routes';

Template.btnpanel.events({
    'click #login': () => {
        Router.go("/login")
    },
    'click #signup': () => {
        Router.go("/signup")
    },
    'click #skip': () => {
        Router.go("/home")
    }
})
