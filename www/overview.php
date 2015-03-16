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

  $points = array();
  $users = User::get_all(2014);
  $periods = VotePeriod::get_all(2014);
  $peasants = Peasant::get_all(2014);
  echo '<div class="overview"><table>';
  echo '<tr>';
  echo '<td>&nbsp;</td>';
  echo '<td>&nbsp;</td>';
  foreach ($users as $user) {
    $name = $user->get_name();
    $points[$name] = 0;
    echo '<td>' . $name . '</td>';
  }
  unset($name);
  echo '</tr>';
  foreach ($periods as $period) {
    $span = '<td rowspan="' . (count($peasants) + 1) . '">' . $period->get_end_date() . '</td>';
    foreach ($peasants as $peasant) {
      echo '<tr>';
      echo $span;
      $span = '';
      echo '<td>' . $peasant->get_name() . '</td>';
      foreach ($users as $user) {
        $title = $user->get_name() . ' - ' . $peasant->get_name() . "\n\n";
        $p = 0;
        $votes = Vote::get_by_user_in_period($user, $period);
        foreach ($votes as $vote) {
          if ($vote->get_peasant()->id == $peasant->id) {
            $p0 = $vote->get_points();
            $p1 = $vote->get_bonus_points();
            $title .= $vote->get_candidate()->get_name() . ': ' . $p0 . ($p1 > 0 ? '+' . $p1 : '') . ' (C' . $vote->get_type()->id . ")\n";
            $p += $p0; // /* disable bonus for now */ + $p1;
          }
        }
        echo '<td title="' . $title .'">' . ($p > 0 ? $p : '&nbsp;') . '</td>';
      }
      echo '</tr>';
    }
    echo '<tr>';
    echo '<td><i>Subtotaal</i></td>';
    foreach ($users as $user) {
      $periodPoints = $user->get_points_by_period($period);
      $points[$user->get_name()] += $periodPoints;
      echo '<td>' . $periodPoints . '</td>';
    }
    echo '</tr>';
  }
  echo '<tr>';
  echo '<td>&nbsp;</td>';
  echo '<td><b>Totaal</b></td>';
  foreach ($users as $user) {
    echo '<td>' . $points[$user->get_name()] . '</td>';
  }
  echo '<tr>';
  echo '</table></div>';

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
