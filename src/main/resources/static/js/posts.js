/**
 * Created by daniel on 6/27/17.
 */
(function ($) {
    request = $.ajax({
        'url': '/ads.json'

    });
    request.done(function (ads) {
        var html = '';
        ads.forEach(function (ad) {
            html += '<div>';
            html += '<h1>' + ad.title + '</h1>';
            html += '<p>' + ad.description + '</p>';
            html += '<p> Published By: ' + ad.author.username + '</p>';
            html += '</div>';
        });
        $('#ads').html(html);
    });
})(jQuery);

(function ($) {
    request = $.ajax({
        'url': '/posts.json'

    });
    request.done(function (posts) {
        var html = '';
        posts.forEach(function (post) {
            html += '<div>';
            html += '<h1>' + post.title + '</h1>';
            html += '<p>' + post.body + '</p>';
            html += '<p> Published By: ' + post.owner.username + '</p>';
            html += '</div>';
        });
        $('#posts').html(html);
    });
})(jQuery);