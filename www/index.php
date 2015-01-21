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

  $year = $USER->get_year();
  $peasants = Peasant::get_all($year);

  $divs = array();

  foreach ($peasants as $peasant) {
    $div = '<div class="container">';
    $div .= $peasant->as_html();
    $candidates = $peasant->get_candidates();
    foreach ($candidates as $candidate) {
      $div .= ' '.$candidate->as_html();
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
  </body>
</html>
