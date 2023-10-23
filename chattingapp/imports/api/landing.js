import { Template } from 'meteor/templating';
import '../../routes/routes';

Template.landing.events({
    'click #login': () => {
        Router.go("/login")
    },
    'click #signup': () => {
        Router.go("/signup")
    }
})
