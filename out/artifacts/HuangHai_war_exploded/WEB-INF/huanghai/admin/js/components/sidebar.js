/**
 * Created by admin on 2017/11/16.
 */
let templateForSidebar =
    '<aside class="sidebar canvas-left">' +
        '<nav class="main-navigation">' +
            '<ul>' +
                '<li v-for="item in links">' +
                    '<a :href="item.url">' +
                        '<i class="fa" :class="item.icon"></i>' +
                        '<span v-text="item.name"></span>' +
                    '</a>' +
                '</li>' +
            '</ul>' +
        '</nav>' +
        '<footer>' +
            '<div class="footer-toolbar pull-left">' +
                '<a href="javascript:;" class="pull-left help">' +
                    '<i class="fa fa-question-circle"></i>' +
                '</a>' +
                '<a href="javascript:;" @click="shrink()" class="toggle-sidebar pull-right hidden-xs">' +
                    '<i class="fa fa-angle-left"></i>' +
                '</a>' +
            '</div>' +
        '</footer>' +
    '</aside>';

let sidebar = {
    template: templateForSidebar,
    props: ["links"],
    methods: {
        shrink: function() {
            $("#client").toggleClass("small-sidebar");
        }
    }
};