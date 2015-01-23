<?php

require_once '../base.php';

?>
<html>
  <head>
    <title>Boer Zoekt Vrouw</title>
    <link rel="stylesheet" type="text/css" href="style.css">
  </head>
  <body>
    <div class="title"></div>
    <div class="user"><?php echo $USER ? $USER->get_name() : ''; ?></div>
    <div class="bzv">

<?php

if ($USER) {

  $period = VotePeriod::get_current();
  $votes = Vote::get_by_user_in_period($USER, $period);

  $message = 'Er is geen actieve stem ronde.';
  if ($period) {
    $date = $period->get_end_date();
    $count = $period->get_vote_count();
    $countText = $period->get_vote_count_as_text();
    $message = 'Stemmen kan tot <b>' . $date . '</b>. '
        . 'Er gaa' . ($count == 1 ? 't' : 'n') . ' <b>' . $countText
        . '</b> boer' . ($count == 1 ? '' : 'en')
        . ' door naar de volgende ronde. '
        . 'Alle wijzigingen worden direct opgeslagen. ';
  }
  echo '<div class="info">' . $message . '</div>';

  $year = $USER->get_year();
  $peasants = Peasant::get_all($year);
  $divs = array();

  foreach ($peasants as $peasant) {
    $div = '<div class="container">';
    $div .= $peasant->as_html($votes);
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
    <div class="login">
      <form method="POST">
        <input name="logout" type="submit" value="Uitloggen">
      </form>
    </div>

<?php

}

?>

  </body>
</html>
