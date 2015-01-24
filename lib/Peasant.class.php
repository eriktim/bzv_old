<?php

class Peasant extends Base {

  const TABLE_NAME = 'bzv_peasants';
  const COLUMN_NAME = 'peasantid';

  public function get_name() {
    return parent::get_value('name');
  }

  public function as_html() {
    return '<div class="peasant pea' . $this->id . '">'
        . '<div class="badge icon"></div></div>';
  }

  public function get_candidates() {
    return Candidate::get_all($this);
  }

  public static function get_all($year) {
    $data = array('year' => $year);
    $q = 'SELECT id FROM ' . self::TABLE_NAME . ' WHERE year=:year ORDER BY id';

    return parent::fetch_array($q, $data);
  }

}
