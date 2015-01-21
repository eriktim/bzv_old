<?php

class Candidate extends Base {

  const TABLE_NAME = 'bzv_candidates';
  const COLUMN_NAME = 'candidateid';

  public function get_name() {
    return parent::get_value('name');
  }

  public function peasant() {
    return parent::get_object(Peasant::c());
  }

  public function is_eliminated() {
    $date = parent::get_value('date_elimination');
    return !!$date;
  }

  public function as_html() {
    $peasant = $this->peasant();
    $disabled = $this->is_eliminated() ? ' disabled' : '';
    return '<div class="candidate pea' . $peasant->id . ' can' . $this->id . $disabled . '"></div>';
  }

  public static function get_all($var) {
    $q = NULL;
    $data = array();

    switch (get_class($var)) {
      case Peasant::c():
        $q = 'SELECT id FROM ' . self::TABLE_NAME . ' WHERE ' . Peasant::COLUMN_NAME. '=:column ORDER BY id';
        $data['column'] = $var->id;
        break;
      default:
        $q = 'SELECT id FROM ' . self::TABLE_NAME . ' WHERE year=:year ORDER BY id';
        $data['year'] = (int) $var;
        break;
    }

    return parent::fetch_array($q, $data);
  }

}
