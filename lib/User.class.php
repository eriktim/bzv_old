<?php

class User extends Base {

  const TABLE_NAME = 'bzv_users';
  const COLUMN_NAME = 'userid';

  public function get_name() {
    return parent::get_value('name');
  }

  public function get_year() {
    return (int) parent::get_value('year');
  }

  public static function login($year, $code) {
    global $db;

    $data = array('year' => $year);

    $st = $db->prepare('SELECT id, hash FROM ' . self::TABLE_NAME
         . ' WHERE year=:year ORDER BY id ASC');
    $st->execute($data);

    if ($st->rowCount() === 0) {
      // slow down the 'attacker'
      password_verify($password, 'dummy');
    } else {
      $st->setFetchMode(PDO::FETCH_ASSOC);
      while($row = $st->fetch()) {
        $hash = $row['hash'];
        if (password_verify($code, $hash)) {
          return new User($row['id']);
        }
      }
    }
    return false;
  }

}
