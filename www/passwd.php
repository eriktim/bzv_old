<html>
  <head>
    <title>Boer Zoekt Vrouw</title>
    <link rel="stylesheet" type="text/css" href="style.css">
  </head>
  <body>
    <div class="title"></div>
    <div>

<?php

require_once '../base.php';

if ($USER && $USER->id === 1) {
  $users = User::get_all(2014);
  $st = $db->prepare('UPDATE ' . User::TABLE_NAME
      . ' SET  hash=:hash WHERE id=:id');

  echo '<ul style="list-style: none;">';
  echo '<li>Regenerating passwords...</li>';

  $codes = array();
  foreach ($users as $user) {
    echo '<li>Processing `' . $user->get_name() . '`... ';
    $code;
    do {
      $code = str_pad(rand(0, 9999), 4, "0", STR_PAD_LEFT);
    } while (in_array($code, $codes));
    $codes[] = $code;
    $hash = password_hash($code, PASSWORD_BCRYPT);
    $data = array(
      'hash' => $hash,
      'id' => $user->id
    );
    echo $st->execute($data) ? $code : 'Failed';
    echo '</li>';
  }
  echo '<li>Done.</li>';
  echo '</ul>';
}

?>

    </div>
  </body>
</html>
