var help = $('#help');
$('#help-toggle').click(function() {
  help.toggle();
});

var icons = ['ico-bad', 'ico-good', 'ico-heart'];
var count = $('#count').val();
$('.container').each(function() {
  var peasant = $(this).find('.peasant > .badge').first();
  var candidates = $(this).find('.candidate');
  var redraw = function () {
    var good = candidates.find('.badge.ico-good').length;
    var heart = candidates.find('.badge.ico-heart').length;
    if (good + heart == count) {
      if (heart <= 1) {
        peasant.removeClass('ico-warning');
        if (heart) {
          peasant.addClass('ico-heart');
        }
      }
    } else {
      peasant.removeClass('ico-heart');
      peasant.addClass('ico-warning');
    }
  }
  redraw();
  candidates.click(function() {
    var badge = $(this).find('.badge').first();
    $.each(icons, function(i, icon) {
      if (badge.hasClass(icon)) {
        badge.removeClass(icon);
        icon = icons[(i + 1) % icons.length];
        badge.addClass(icon);
        return false;
      }
    });
    redraw();
  });
});
