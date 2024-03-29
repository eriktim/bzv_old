<html>
  <head>
    <title>Boer Zoekt Vrouw</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <script src="jquery-2.1.3.min.js"></script>
  </head>
  <body>
    <div class="title"></div>

<?php

require_once '../base.php';

?>
    <div class="user"><?php echo $USER ? $USER->get_name() : ''; ?></div>
    <div class="bzv">

<?php

if ($USER) {

  echo '<input type="hidden" id="userid" value="' . $USER->id . '">';

  $period = VotePeriod::get_current();
  $votes = Vote::get_by_user_in_period($USER, $period);

  $message = 'Er is geen actieve stem ronde.';
  if ($period) {
    $count = $period->get_vote_count();
    echo '<input type="hidden" id="count" value="' . $count . '">';

    $date = $period->get_end_date();
    $countText = $period->get_vote_count_as_text();
    $message = '<div>Stemmen kan tot <b>' . $date . '</b>.<br>'
        . 'Er ' . ($count == 1 ? 'mag' : 'mogen') . ' maximaal <b>'
        . $countText . '</b> kandida' . ($count == 1 ? 'at' : 'ten')
        . ' door naar de volgende ronde. '
        . '<span id="help-toggle">[...]</span></div>'
        . '<div id="help"><ul>'
        . '<li>Alle wijzigingen worden direct opgeslagen.</li>'
        . '<li><div class="icon ico-bad"></div><span>Deze kandidaat gaat NIET door.</span></li>'
        . '<li><div class="icon ico-good"></div><span>Deze kandidaat gaat WEL door.</span></li>'
        . '<li><div class="icon ico-heart"></div><span>Deze kandidaat gaat WEL door én is de uiteindelijk winnaar.</span></li>'
        . '<li><div class="icon ico-warning"></div><span>De stemmen voor deze boer zijn niet geldig. Controleer deze en pas ze aan.</span></li>'
        . '</ul></div>';
  }
  echo '<div class="info">' . $message . '</div>';

  $year = $USER->get_year();
  $peasants = Peasant::get_all($year);
  $divs = array();

  foreach ($peasants as $peasant) {
    $div = '<div class="container">';
    $div .= $peasant->as_html();
    $candidates = $peasant->get_candidates();
    foreach ($candidates as $candidate) {
      $div .= ' '.$candidate->as_html($votes);
    }
    $div .= '</div>';
    $divs[] = $div;
  }

  echo implode('<div class="separator"></div>', $divs);

?>

    </div>
    <div class="center">
      <form method="POST">
        <input name="logout" type="submit" value="Uitloggen">
      </form>
    </div>

<?php

}

?>

    <script src="bzv.js"></script>
  </body>
</html>
