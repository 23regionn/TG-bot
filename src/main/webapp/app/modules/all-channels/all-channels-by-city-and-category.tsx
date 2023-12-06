import React, { useState, useEffect, useRef } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';

import { IRootState } from 'app/shared/reducers';
import {
  createCategory,
  getEntityCategoryById,
  getInfoAboutCategoryAndCity,
  getOnlyCategories,
  partialUpdateCategory,
} from '../all-categories/all-categories.reducer';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Button } from 'primereact/button';
import { Dialog } from 'primereact/dialog';
import { InputText } from 'primereact/inputtext';
import { InputTextarea } from 'primereact/inputtextarea';

import { TriStateCheckbox } from 'primereact/tristatecheckbox';
import {
  createChannelForCategoryPage,
  getChannelsByCategoryId,
  getChannelsByCityIdAndCategoryId,
  partialUpdateChannelForChannelsByCategoryAndCityPage,
} from 'app/entities/chanell/chanell.reducer';
import { NavLink } from 'reactstrap';
import { ICity } from 'app/shared/model/city.model';
import { IManager } from 'app/shared/model/manager.model';
import { Calendar } from 'primereact/calendar';

// Primereact
// Primereact
// Primereact
export interface IAllChannelsByCityAndCategoryProps
  extends StateProps,
    DispatchProps,
    RouteComponentProps<{
      idCity: string;
      id: string;
      idCat: string;
    }> {}

export const AllChannelsByCityAndCategory = (props: IAllChannelsByCityAndCategoryProps) => {
  const [channel, setChannel] = useState(null);
  const [category, setCategory] = useState(null);
  const [editChannelDialog, setEditChannelDialog] = useState(false);
  const [createChanDialog, setCreateChanDialog] = useState(false);
  const [startDateState, setStartDateState] = useState(null);
  const [lastPayDateState, setLastPayDateState] = useState(null);
  const [endPublicDateState, setEndPublicDateState] = useState(null);
  const nameChannelValue = useRef(null);
  const linkToChannelValue = useRef(null);
  const scoreValue = useRef(null);
  const priceForPayValue = useRef(null);
  const commentValue = useRef(null);
  const contactsValue = useRef(null);
  const [isFirstState, setIsFirstState] = useState(false);
  const [isModerateState, setIsModerateState] = useState(false);
  const [isPayState, setIsPayState] = useState(false);
  const [isShowState, setIsShowState] = useState(false);

  useEffect(() => {
    props.getChannelsByCityIdAndCategoryId(props.match.params.idCity, props.match.params.idCat);
    props.getInfoAboutCategoryAndCity(props.match.params.idCity, props.match.params.idCat).then(cat => {
      setCategory(cat.value.data);
      window.console.log(cat.value.data, 'cat');
      window.console.log(channel, 'category');
    });
  }, []);

  const { chanellList, match, loading } = props;

  const editChannel = rowData => {
    window.console.log(rowData, 'rowData222');

    setChannel(rowData);
    setIsModerateState(rowData?.isModerate);
    setStartDateState(new Date(rowData?.startDate));
    setLastPayDateState(new Date(rowData?.lastPayDate));
    setEndPublicDateState(new Date(rowData?.endPublicDate));
    setIsPayState(rowData?.isPay);
    setEditChannelDialog(true);
  };

  const createChanFunc = () => {
    setChannel(null);
    setIsModerateState(false);
    setIsPayState(false);
    setStartDateState(null);
    setLastPayDateState(null);
    setEndPublicDateState(null);

    setCreateChanDialog(true);
  };

  const hideDialog = () => {
    setChannel(null);
    nameChannelValue.current.value = null;
    scoreValue.current.value = null;
    linkToChannelValue.current.value = null;
    scoreValue.current.value = null;
    priceForPayValue.current.value = null;
    commentValue.current.value = null;
    contactsValue.current.value = null;

    setIsModerateState(false);
    setStartDateState(null);
    setLastPayDateState(null);
    setEndPublicDateState(null);
    setIsPayState(false);

    setEditChannelDialog(false);
  };

  const hideCreateDialog = () => {
    nameChannelValue.current.value = null;
    scoreValue.current.value = null;
    linkToChannelValue.current.value = null;
    scoreValue.current.value = null;
    priceForPayValue.current.value = null;
    commentValue.current.value = null;
    contactsValue.current.value = null;

    setIsModerateState(false);
    setStartDateState(null);
    setLastPayDateState(null);
    setEndPublicDateState(null);
    setIsPayState(false);

    setCreateChanDialog(false);
  };

  const editCategoryButton = () => {
    const entity = {
      idCat: props.match.params.idCat,
      idCity: props.match.params.idCity,
      id: channel.id,
      name: nameChannelValue.current.value,
      score: scoreValue.current.value,
      link: linkToChannelValue.current.value,
      priceForPay: priceForPayValue.current.value,
      comment: commentValue.current.value,
      contacts: contactsValue.current.value,
      isModerate: isModerateState,
      isPay: isPayState,
      startDate: startDateState,
      lastPayDate: lastPayDateState,
      endPublicDate: endPublicDateState,
    };

    props.partialUpdateChannelForChannelsByCategoryAndCityPage(entity);

    setChannel(null);
    nameChannelValue.current.value = null;
    scoreValue.current.value = null;
    scoreValue.current.value = null;
    linkToChannelValue.current.value = null;
    priceForPayValue.current.value = null;
    commentValue.current.value = null;
    contactsValue.current.value = null;

    setIsModerateState(false);
    setIsPayState(false);
    setStartDateState(null);
    setLastPayDateState(null);
    setEndPublicDateState(null);

    setEditChannelDialog(false);
  };

  const createCategoryButton = () => {
    // НУЖЕН ДРУГОЙ МЕТОД, КОТОРЫЙ СОЗДАЁТ КАНАЛ ДЛЯ КАТЕГОРИИ И ГОРОДА
    // НУЖЕН ДРУГОЙ МЕТОД, КОТОРЫЙ СОЗДАЁТ КАНАЛ ДЛЯ КАТЕГОРИИ И ГОРОДА
    // НУЖЕН ДРУГОЙ МЕТОД, КОТОРЫЙ СОЗДАЁТ КАНАЛ ДЛЯ КАТЕГОРИИ И ГОРОДА
    const entity = {
      idCat: props.match.params.id,
      name: nameChannelValue.current.value,
      score: scoreValue.current.value,
      link: linkToChannelValue.current.value,
      priceForPay: priceForPayValue.current.value,
      comment: commentValue.current.value,
      contacts: contactsValue.current.value,
      isModerate: isModerateState,
      isPay: isPayState,
      lastPayDate: lastPayDateState,
      endPublicDate: endPublicDateState,
    };

    props.createChannelForCategoryPage(entity);

    nameChannelValue.current.value = null;
    scoreValue.current.value = null;
    scoreValue.current.value = null;
    linkToChannelValue.current.value = null;
    priceForPayValue.current.value = null;
    commentValue.current.value = null;
    contactsValue.current.value = null;

    setIsModerateState(false);
    setIsPayState(false);
    setStartDateState(null);
    setLastPayDateState(null);
    setEndPublicDateState(null);

    setCreateChanDialog(false);
  };

  const editChannelDialogFooter = (
    <React.Fragment>
      <Button label="Cancel" icon="pi pi-times" className="p-button-text" onClick={hideDialog} />
      <Button label="Save" icon="pi pi-check" className="p-button-text" onClick={editCategoryButton} />
    </React.Fragment>
  );

  const createCategoryDialogFooter = (
    <React.Fragment>
      <Button label="Cancel" icon="pi pi-times" className="p-button-text" onClick={hideCreateDialog} />
      <Button label="Save" icon="pi pi-check" className="p-button-text" onClick={createCategoryButton} />
    </React.Fragment>
  );

  const actionBodyTemplate = rowData => {
    return (
      <React.Fragment>
        <Button icon="pi pi-pencil" className="p-button-rounded p-button-success p-mr-2" onClick={() => editChannel(rowData)} />
      </React.Fragment>
    );
  };

  return (
    <div>
      <div style={{ display: 'flex', alignItems: 'center' }}>
        <div style={{ flexGrow: 1, textAlign: 'center', fontSize: '1.4rem' }}>
          {category?.cityName} - Каналы категории: {category?.categoryName}
        </div>
        <div>
          <Button id="button_basic" label="Создать канал" onClick={createChanFunc} />
        </div>
      </div>

      <br />
      <DataTable value={chanellList as any[]} sortMode="multiple" className="oi-p-datatable">
        <Column headerStyle={{ width: '5rem' }} field="id" header="№"></Column>
        <Column field="name" filter filterPlaceholder="Поиск по наименованию" sortable header="Имя канала"></Column>
        <Column
          field="link"
          style={{ maxWidth: '15vw', maxHight: '5vw' }}
          header="Ссылка"
          body={rowData => (
            <NavLink href={rowData?.link} target="_blank">
              {rowData?.link}
            </NavLink>
          )}
        ></Column>
        <Column
          field="isModerate"
          sortable
          header="isModerate"
          style={{ width: '2vw' }}
          body={rowData => (rowData.isModerate !== null ? (rowData.isModerate ? 'Да' : 'Нет') : 'null')}
        ></Column>
        <Column
          field="score"
          sortable
          header="Порядковый номер"
          style={{ width: '7.5vw' }}
          body={rowData => (rowData.score !== null ? rowData.score : 'null')}
        ></Column>
        <Column
          field="endPublicDate"
          header="Конечная дата"
          sortable
          dataType="date"
          body={rowData => new Date(rowData.endPublicDate).toLocaleDateString()}
        ></Column>
        <Column
          field="isPay"
          sortable
          header="Размещение"
          style={{ width: '2vw' }}
          body={rowData => (rowData.isPay !== null ? (rowData.isPay ? 'Да' : 'Нет') : 'null')}
        ></Column>
        <Column
          field="comment"
          header="Комментарий"
          body={rowData => <InputTextarea value={rowData?.comment} readOnly={true} rows={2} cols={20} />}
        ></Column>

        <Column body={actionBodyTemplate} exportable={false} style={{ minWidth: '8rem' }}></Column>
      </DataTable>

      <Dialog
        visible={editChannelDialog}
        style={{ width: '600px' }}
        header={'Канал - ' + channel?.name}
        modal
        className="p-fluid"
        footer={editChannelDialogFooter}
        onHide={hideDialog}
        dismissableMask={true}
      >
        <div className="p-field">
          <div> Наименование канала </div>
          <InputText defaultValue={channel?.name || ''} placeholder={'Введите название канала'} ref={nameChannelValue} />
        </div>

        <br />

        <div className="p-field">
          <div> Ссылка </div>
          <InputText defaultValue={channel?.link || ''} placeholder={'Введите ссылку на канал'} ref={linkToChannelValue} />
        </div>

        <br />

        <div className="p-field">
          <div> Порядковый номер </div>
          <InputText defaultValue={channel?.score || ''} placeholder={'Введите порядковый номер'} ref={scoreValue} />
        </div>

        <br />

        <div className="card">
          Прошел модерацию - isModerate ?
          <div className="p-field-checkbox p-m-0">
            <TriStateCheckbox value={isModerateState} onChange={e => setIsModerateState(e.value)} />
            <div>{String(isModerateState)}</div>
          </div>
        </div>

        <br />

        <div className="card">
          Размещение - isPay ?
          <div className="p-field-checkbox p-m-0">
            <TriStateCheckbox value={isPayState} onChange={e => setIsPayState(e.value)} />
            <div>{String(isPayState)}</div>
          </div>
        </div>

        <br />

        <div className="p-field">
          <div> Стоимость </div>
          <InputText defaultValue={channel?.priceForPay || ''} placeholder={'Введите стоимость'} ref={priceForPayValue} />
        </div>

        <br />
        <div className="p-field">
          <div>Дата размещения ссылки</div>
          <Calendar
            id="basic"
            value={startDateState}
            onChange={e => setStartDateState(e.value)}
            showIcon
            dateFormat="dd/mm/yy"
            disabled={true}
          />
        </div>
        <br />

        <div className="p-field">
          <div> Дата последнего размещения </div>
          <Calendar id="basic" value={lastPayDateState} onChange={e => setLastPayDateState(e.value)} showIcon dateFormat="dd/mm/yy" />
        </div>

        <br />

        <div className="p-field">
          <div> Конечная дата размещения </div>
          <Calendar id="basic" value={endPublicDateState} onChange={e => setEndPublicDateState(e.value)} showIcon dateFormat="dd/mm/yy" />
        </div>

        <br />

        <div className="p-field">
          <div> Комментарий </div>
          <InputTextarea value={channel?.comment} rows={2} cols={20} ref={commentValue} />
        </div>

        <br />

        <div className="p-field">
          <div> Контакт </div>
          <InputText defaultValue={channel?.contacts || ''} placeholder={'Введите контактные данные'} ref={contactsValue} />
        </div>

        <br />
      </Dialog>

      <Dialog
        visible={createChanDialog}
        style={{ width: '600px' }}
        header={'Создание канала в категории: ' + category?.name}
        modal
        className="p-fluid"
        footer={createCategoryDialogFooter}
        onHide={hideCreateDialog}
        dismissableMask={true}
      >
        <div className="p-field">
          <div> Наименование канала </div>
          <InputText placeholder={'Введите название канала'} ref={nameChannelValue} />
        </div>

        <br />

        <div className="p-field">
          <div> Ссылка </div>
          <InputText placeholder={'Введите ссылку на канал'} ref={linkToChannelValue} />
        </div>

        <br />

        <div className="p-field">
          <div> Порядковый номер </div>
          <InputText placeholder={'Введите порядковый номер'} ref={scoreValue} />
        </div>

        <br />

        <div className="card">
          Прошел модерацию - isModerate ?
          <div className="p-field-checkbox p-m-0">
            <TriStateCheckbox value={isModerateState} onChange={e => setIsModerateState(e.value)} />
            <div>{String(isModerateState)}</div>
          </div>
        </div>

        <br />

        <div className="card">
          Размещение - isPay ?
          <div className="p-field-checkbox p-m-0">
            <TriStateCheckbox value={isPayState} onChange={e => setIsPayState(e.value)} />
            <div>{String(isPayState)}</div>
          </div>
        </div>

        <br />

        <div className="p-field">
          <div> Стоимость </div>
          <InputText placeholder={'Введите стоимость'} ref={priceForPayValue} />
        </div>

        <br />

        <div className="p-field">
          <div> Дата последнего размещения </div>
          <Calendar id="basic" value={lastPayDateState} onChange={e => setLastPayDateState(e.value)} showIcon dateFormat="dd/mm/yy" />
        </div>

        <br />

        <div className="p-field">
          <div> Конечная дата размещения </div>
          <Calendar id="basic" value={endPublicDateState} onChange={e => setEndPublicDateState(e.value)} showIcon dateFormat="dd/mm/yy" />
        </div>

        <br />

        <div className="p-field">
          <div> Комментарий </div>
          <InputTextarea rows={2} cols={20} ref={commentValue} />
        </div>

        <br />

        <div className="p-field">
          <div> Контакт </div>
          <InputText placeholder={'Введите контактные данные'} ref={contactsValue} />
        </div>

        <br />
      </Dialog>
    </div>
  );
};

const mapStateToProps = ({ chanell }: IRootState) => ({
  chanellList: chanell.entities,
  loading: chanell.loading,
});

const mapDispatchToProps = {
  getChannelsByCityIdAndCategoryId,
  getInfoAboutCategoryAndCity,
  // getEntityCategoryById,
  // partialUpdateChannelForCategoryPage,
  partialUpdateChannelForChannelsByCategoryAndCityPage,
  createChannelForCategoryPage,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AllChannelsByCityAndCategory);
