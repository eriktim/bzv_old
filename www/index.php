<?php

require_once '../base.php';

$peasants = Peasant::get_all(2014);

?>
<html>
  <head>
    <title>Boer Zoekt Vrouw</title>
    <link rel="stylesheet" type="text/css" href="style.css">
  </head>
  <body>
    <div class="bzv">

<?php

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
  </body>
</html>
