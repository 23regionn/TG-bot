import { Calendar } from 'primereact/calendar';
import { Dropdown } from 'primereact/dropdown';
import React from 'react';
import './calendar-component.scss';

interface InitializationParams {
  options: any;
  onSelect?: any;
  onChange?: any;
  numberOfMonths?: any;
}

export const CalendarComponent = (props: InitializationParams) => {
  const monthNavigatorTemplate = (e: any) => {
    return (
      <Dropdown
        value={e.value}
        options={e.options}
        onChange={event => e.onChange(event.originalEvent, event.value)}
        className="dropdownStyle1"
      />
    );
  };

  const yearNavigatorTemplate = (e: any) => {
    return (
      <Dropdown
        value={e.value}
        options={e.options}
        onChange={event => e.onChange(event.originalEvent, event.value)}
        className="dropdownStyle1"
      />
    );
  };
  return (
    <Calendar
      id="calendarStyle1"
      // className="inputs_text"
      value={props.options.value}
      onSelect={props.onSelect ? e => props.onSelect(e.value, props.options.index) : null}
      onChange={props.onChange ? e => props.onChange(e.value, props.options.index) : null}
      // locale={'ru'}
      monthNavigator
      yearNavigator
      monthNavigatorTemplate={monthNavigatorTemplate}
      yearNavigatorTemplate={yearNavigatorTemplate}
      showButtonBar
      numberOfMonths={props.numberOfMonths ? props.numberOfMonths : 1}
      panelClassName="calendarStyle1"
      required={true}
      yearRange="2010:2030"
      dateFormat="dd.mm.yy"
      placeholder="дд/мм/гггг"
      mask="99/99/9999"
    />
  );
};
