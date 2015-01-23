<?php

class Candidate extends Base {

  const TABLE_NAME = 'bzv_candidates';
  const COLUMN_NAME = 'candidateid';

  public function get_name() {
    return parent::get_value('name');
  }

  public function get_peasant() {
    return parent::get_object(Peasant::c());
  }

  public function is_eliminated() {
    $date = parent::get_value('date_elimination');
    return !!$date;
  }

  public function as_html($votes) {
    $peasant = $this->get_peasant();
    $eliminated = $this->is_eliminated();
    $disabled = '';
    $icon = '';
    if ($eliminated) {
      $disabled = ' disabled';
    } else {
      $icon = count($votes) > 0 ? 'ico-bad' : '';
      foreach ($votes as $vote) {
        if ($vote->get_candidate()->id == $this->id) {
          switch ($vote->get_type()->id) {
            case VoteType::NORMAL:
              $icon = 'ico-good';
              break;
            case VoteType::WINNER:
              $icon = 'ico-heart';
              break;
          }
          break;
        }
      }
    }
    return '<div class="candidate pea' . $peasant->id . ' can' . $this->id
        . $disabled . '"><div class="icon ' . $icon . '"></div></div>';
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
