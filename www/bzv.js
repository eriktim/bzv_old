var help = $('#help');
$('#help-toggle').click(function() {
  help.toggle();
});

var icons = ['ico-bad', 'ico-good', 'ico-heart'];
var data = {};

data.userid = $('#userid').val();
var count = $('#count').val();

$('.container').each(function() {
  var peasant = $(this).find('.peasant > .badge').first();
  var candidates = $(this).find('.candidate');
  var redraw = function () {
    var good = candidates.find('.badge.ico-good').length;
    var heart = candidates.find('.badge.ico-heart').length;
    if (good + heart == count) {
      peasant.removeClass('ico-warning');
      if (heart > 1) {
        peasant.addClass('ico-warning');
      } else if (heart == 1) {
        peasant.addClass('ico-heart');
      }
    } else {
      peasant.removeClass('ico-heart');
      peasant.addClass('ico-warning');
    }
  }
  redraw();
  candidates.click(function() {
    var badge = $(this).find('.badge').first();
    var index = 0;
    $.each(icons, function(i, icon) {
      if (badge.hasClass(icon)) {
        index = (i + 1) % icons.length;
        badge.removeClass(icon);
        return false;
      }
    });
    icon = icons[index];
    badge.addClass(icon);
    redraw();

    data.candidateid = $(this).attr('id');
    data.typeid = index;
    $.post("change.php", data).fail(function() {
      alert("Je wijziging is NIET opgeslagen. "
          + "Herlaadt de pagina en probeer het opnieuw");
    });
  });
});
