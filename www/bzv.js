var help = $('#help');
$('#help-toggle').click(function() {
  help.toggle();
});

var icons = ['ico-bad', 'ico-good', 'ico-heart'];
var data = {};

data.userid = $('#userid').val();
var count = $('#count').val();

if (count > 0) {
  $('.container').each(function() {
    var peasant = $(this).find('.peasant > .badge').first();
    var candidates = $(this).find('.candidate').not('.disabled');
    var redraw = function () {
      var bad = candidates.find('.badge.ico-bad').length;
      var good = candidates.find('.badge.ico-good').length;
      var heart = candidates.find('.badge.ico-heart').length;
      if (bad + good + heart == candidates.length && good + heart <= count) {
        peasant.removeClass('ico-warning');
        if (heart > 1) {
          peasant.addClass('ico-warning');
        }
      } else {
        peasant.addClass('ico-warning');
      }
    }
    redraw();
    candidates.click(function() {
      var badge = $(this).find('.badge').first();
      var index = 0;
      $.each(icons, function(i, icon) {
        if (badge.hasClass(icon)) {
          badge.removeClass(icon);
          index = (i + 1) % icons.length;
          return false;
        }
      });
      icon = icons[index];
      badge.addClass(icon);
      redraw();

      data.candidateid = $(this).attr('id');
      data.typeid = index + 1;
      $.post("change.php", data).fail(function() {
        alert("Je wijziging is NIET opgeslagen. "
            + "Herlaadt de pagina en probeer het opnieuw");
      });
    });
  });
}

var login = $('.login');
if (login) {
  var input = login.find('input').first();
  input.click(function() {
   input.val('');
  });

  var nums = login.find('li');
  nums.click(function() {
    var val = input.val() + $(this).text();
    input.val(val);
    if (val.length == 4) {
      login.hide();
      login.find('form').first().submit();
    }
  });
}
