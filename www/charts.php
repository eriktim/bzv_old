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

  $voteCount = 0;
  $period = VotePeriod::get_last();
  if ($period) {
    $peasants = Peasant::get_all(2014);
    foreach ($peasants as $peasant) {
      foreach ($peasant->get_candidates() as $candidate) {
        if (!$candidate->is_eliminated()) {
          $voteCount++;
        }
      }
    }
  }
  $users = User::get_all(2014);
  $charts = array();
  $incomplete = array();
  $complete = array();
  foreach ($users as $user) {
    $points = $user->get_points();
    $name = $user->get_name();
    if (!$charts[$points]) {
      $charts[$points] = array();
    }
    $charts[$points][] = $name;
    if ($period) {
      $votes = Vote::get_by_user_in_period($user, $period);
      if (count($votes) < $voteCount) {
        $incomplete[] = $name;
      } else {
        $complete[] = $name;
      }
    }
  }

  krsort($charts);
  foreach ($charts as $points=>$names) {
    echo '<ul class="charts">';
    echo '<li class="points">' . $points . '</li>';
    foreach ($names as $name) {
      echo '<li>' . $name . '</li>';
    }
    echo '</ul>';
  }

  if ($period) {
    sort($incomplete);
    sort($complete);
    echo '<ul class="charts">';
    echo '<li class="points">Compleet</li>';
    foreach ($complete as $name) {
      echo '<li>' . $name . '</li>';
    }
    echo '</ul>';
    echo '<ul class="charts">';
    echo '<li class="points">Niet compleet</li>';
    foreach ($incomplete as $name) {
      echo '<li>' . $name . '</li>';
    }
    echo '</ul>';
  }

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
