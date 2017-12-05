/**
 * Created by admin on 2017/12/2.
 */
function Validator() {
    this.pages = function(pages, maxPages) {
        pages = Number(pages);
        maxPages = Number(maxPages);
        return !(!/[0-9]+/.test(pages) || pages < 1 || pages > maxPages);
    };
    this.pageNumber = function(number) {
        number = Number(number);
        return !(!/[0-9]+/.test(number) || number < 1);
    };
}