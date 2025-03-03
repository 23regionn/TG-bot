import React, { useState, useEffect, useRef } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';

import { IRootState } from 'app/shared/reducers';
import { Button } from 'primereact/button';
import { Dialog } from 'primereact/dialog';
import { InputText } from 'primereact/inputtext';
import { InputTextarea } from 'primereact/inputtextarea';
import { TriStateCheckbox } from 'primereact/tristatecheckbox';
import {
  createChannelForChannelPage,
  getAllChanellsInfoDTO,
  partialUpdateChannelForChannelPage,
  getChannelsPage,
} from 'app/entities/chanell/chanell.reducer';
import { Calendar } from 'primereact/calendar';
import { IPaginator } from 'app/shared/util/pagination.constants';
import { ISort } from 'app/shared/util/sort-constants';
import { TableComponentNew } from 'app/shared/component/LofTA-component/table/table-lazy/table-component-new';
import { IFilter } from 'app/shared/util/constant';

// Primereact
// Primereact
// Primereact
export interface IAllChannelsProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AllChannels = (props: IAllChannelsProps) => {
  const [channel, setChannel] = useState(null);
  const [category, setCategory] = useState(null);
  const [editChannelDialog, setEditChannelDialog] = useState(false);
  const [createChanDialog, setCreateChanDialog] = useState(false);
  const [startDateState, setStartDateState] = useState(null);
  const [lastPayDateState, setLastPayDateState] = useState(null);
  const [endPublicDateState, setEndPublicDateState] = useState(null);
  const nameChannelValue = useRef(null);
  const linkToChannelValue = useRef(null);
  const priceForPayValue = useRef(null);
  const commentValue = useRef(null);
  const contactsValue = useRef(null);
  const [isModerateState, setIsModerateState] = useState(false);
  const [isPayState, setIsPayState] = useState(false);

  // const paginatorJson: IPaginator = { numberPage: 1, countElement: 5 };

  useEffect(() => {
    // props.getAllChanellsInfoDTO();
    // props.getChannelsPage(paginatorJson);
  }, []);

  const { chanellList, match, loading, chanellsPage } = props;

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
    linkToChannelValue.current.value = null;
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
    linkToChannelValue.current.value = null;
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
      id: channel.id,
      name: nameChannelValue.current.value,
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

    props.partialUpdateChannelForChannelPage(entity);

    setChannel(null);
    nameChannelValue.current.value = null;
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
    const entity = {
      name: nameChannelValue.current.value,
      link: linkToChannelValue.current.value,
      priceForPay: priceForPayValue.current.value,
      comment: commentValue.current.value,
      contacts: contactsValue.current.value,
      isModerate: isModerateState,
      isPay: isPayState,
      lastPayDate: lastPayDateState,
      endPublicDate: endPublicDateState,
    };

    props.createChannelForChannelPage(entity);

    nameChannelValue.current.value = null;
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

  const [lastSort, setLastSort] = useState(null);
  const [lastPaginator, setLastPaginator] = useState(null);

  /*const options = [
    { name: translate('eaistkApp.listOfTechnicalAcceptances.archive.false'), value: false },
    { name: translate('eaistkApp.listOfTechnicalAcceptances.archive.true'), value: true },
  ];

  const [valueArc, setValueArc] = useState(options[0].value);
  const changeArc = e => {
    setValueArc(e.value);
    getInspection(lastPaginator, lastSort, e.value);
  };*/

  const getInspection = (pagination: IPaginator, sort: ISort) => {
    setLastSort(sort);
    setLastPaginator(pagination);
    /*let archive;
    if (isArchive === null) {
      archive = valueArc ? valueArc : false;
    } else {
      archive = isArchive;
    }*/
    // props.getInspectionsPage(pagination, archive, filtersJsonForm, sort.sortOrder ? sort : null);
    // props.getChannelsPage(paginatorJson);
    props.getChannelsPage(pagination, filtersJsonForm, sort.sortOrder ? sort : null);
  };

  const arrayColumn = [
    {
      field: 'id',
      header: 'ID',
      sortField: 'id',
      sortable: true,
      filter: false,
      filterPlaceholder: null,
      filterMatchMode: null,
      showFilterMenuOptions: false,
      showFilterMenu: false,
      showClearButton: false,
      style: { maxWidth: '5%', minWidth: '5%', textAlign: 'center' },
    },
    {
      field: 'name',
      header: 'Наименование',
      sortable: true,
      sortField: 'name',
      filter: true,
      filterPlaceholder: 'Поиск по названию',
      filterMatchMode: 'contains',
      showFilterMenuOptions: false,
      showFilterMenu: false,
      showClearButton: true,
      style: { minWidth: '10%', maxWidth: '10%' },
    },
    {
      field: 'link',
      header: 'Ссылка',
      sortable: false,
      sortField: 'link',
      filter: true,
      filterPlaceholder: 'Поиск по ссылке',
      filterMatchMode: 'contains',
      showFilterMenuOptions: false,
      showFilterMenu: false,
      showClearButton: true,
      style: { minWidth: '10%', maxWidth: '10%' },
    },
    {
      field: 'isModerate',
      header: 'Модерация',
      sortable: true,
      sortField: 'isModerate',
      filter: false,
      filterPlaceholder: 'Поиск по модерации',
      filterMatchMode: 'contains',
      showFilterMenuOptions: false,
      showFilterMenu: false,
      showClearButton: false,
      style: { minWidth: '1%', maxWidth: '10%' },
    },
    {
      field: 'isPay',
      header: 'Платный',
      sortable: true,
      sortField: 'isPay',
      filter: false,
      filterPlaceholder: 'Поиск по платному',
      filterMatchMode: 'contains',
      showFilterMenuOptions: false,
      showFilterMenu: false,
      showClearButton: false,
      style: { minWidth: '1%', maxWidth: '10%' },
    },
    {
      field: 'lastPayDate',
      header: 'Последнее размещение',
      sortable: true,
      sortField: 'lastPayDate',
      filter: true,
      useDate: true,
      filterPlaceholder: '',
      filterMatchMode: 'dateIs',
      showFilterMenuOptions: false,
      showFilterMenu: false,
      showClearButton: false,
      style: { minWidth: '12%', maxWidth: '12%' },
    },
    {
      field: 'endPublicDate',
      header: 'Конечная дата',
      sortable: true,
      sortField: 'endPublicDate',
      filter: true,
      useDate: true,
      filterPlaceholder: null,
      filterMatchMode: 'dateIs',
      showFilterMenuOptions: false,
      showFilterMenu: false,
      showClearButton: false,
      style: { minWidth: '12%', maxWidth: '12%' },
    },
    {
      field: 'contacts',
      header: 'Контакт',
      sortable: true,
      sortField: 'contacts',
      filter: true,
      filterPlaceholder: 'Поиск по контакту',
      filterMatchMode: 'contains',
      showFilterMenuOptions: false,
      showFilterMenu: false,
      showClearButton: true,
      style: { minWidth: '5%', maxWidth: '18%' },
    },
    {
      field: 'comment',
      header: 'Комментарий',
      sortable: false,
      sortField: 'comment',
      filter: false,
      filterPlaceholder: 'Поиск по комментарию',
      filterMatchMode: 'contains',
      showFilterMenuOptions: false,
      showFilterMenu: false,
      showClearButton: true,
      style: { minWidth: '5%', maxWidth: '18%' },
    },
  ];

  const arrayColumnBody = [
    /*{ body: isArchiveItem, style: { minWidth: '4%', maxWidth: '4%', textAlign: 'center' } },
    { body: editItemDialog, style: { minWidth: '4%', maxWidth: '4%', textAlign: 'center' } },
    { body: btnOfLogs, style: { minWidth: '4%', maxWidth: '4%', textAlign: 'center' } },
    { body: viewItemTemplate, style: { minWidth: '13%', maxWidth: '13%', textAlign: 'center' } },*/
  ];

  const [lazyParamsFilter, setLazyParamsFilter] = useState({
    filters: {
      /*inspectionInvIdName: { value: '', matchMode: 'contains' },
      entityHierarchyName: { value: '', matchMode: 'contains' },*/
      name: { value: '', matchMode: 'contains' },
      link: { value: '', matchMode: 'contains' },
      lastPayDate: { value: '', matchMode: 'dateIs' },
      endPublicDate: { value: '', matchMode: 'dateIs' },
    },
  });
  const filtersJsonForm: IFilter = {
    /*inspectionInvIdName: lazyParamsFilter.filters['inspectionInvIdName'].value,
    entityHierarchyName: lazyParamsFilter.filters['entityHierarchyName'].value,*/
    name: lazyParamsFilter.filters['name'].value,
    link: lazyParamsFilter.filters['link'].value,
    lastPayDate:
      lazyParamsFilter.filters['lastPayDate'].value !== '' &&
      lazyParamsFilter.filters['lastPayDate'].value !== null &&
      lazyParamsFilter.filters['lastPayDate'].value !== undefined
        ? new Date(lazyParamsFilter.filters['lastPayDate'].value).toLocaleDateString()
        : '',
    endPublicDate:
      lazyParamsFilter.filters['endPublicDate'].value !== '' &&
      lazyParamsFilter.filters['endPublicDate'].value !== null &&
      lazyParamsFilter.filters['endPublicDate'].value !== undefined
        ? new Date(lazyParamsFilter.filters['endPublicDate'].value).toLocaleDateString()
        : '',
  };

  const onFilter = event => {
    setLazyParamsFilter(event);
  };

  return (
    <div>
      <div style={{ display: 'flex', alignItems: 'center' }}>
        <div style={{ flexGrow: 1, textAlign: 'center', fontSize: '1.4rem' }}>Все каналы</div>
        <div>
          <Button id="button_basic" label="Создать канал" onClick={createChanFunc} />
        </div>
      </div>

      <br />

      <div className="list-acceptances-table-block">
        <TableComponentNew
          item={chanellsPage}
          getItem={getInspection}
          column={arrayColumn}
          columnBody={arrayColumnBody}
          lazyParamsFilter={lazyParamsFilter}
          onFilter={onFilter}
          frozenValue={true}
          rows={5}
          // loading={loadInspectionPage}
          loading={loading}
          arrayCount={[5, 10, 15]}
        ></TableComponentNew>
      </div>
      {/*<DataTable value={chanellList as any[]} sortMode="multiple" className="oi-p-datatable">
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
          style={{ maxWidth: '15vw' }}
          // body={rowData => <InputTextarea value={rowData?.comment}  rows={2} cols={20} />}
          body={rowData => rowData?.comment}
        ></Column>

        <Column body={actionBodyTemplate} exportable={false} style={{ minWidth: '8rem' }}></Column>
      </DataTable>*/}

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
          <InputTextarea defaultValue={channel?.comment} rows={2} cols={20} ref={commentValue} />
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
        header={'Создание нового канала'}
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
  chanellsPage: chanell.chanellsPage,
});

const mapDispatchToProps = {
  partialUpdateChannelForChannelPage,
  createChannelForChannelPage,
  getAllChanellsInfoDTO,
  getChannelsPage,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AllChannels);
