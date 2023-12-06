import React, { useState, useEffect, useRef } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';

import { IRootState } from 'app/shared/reducers';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Button } from 'primereact/button';
import { Dialog } from 'primereact/dialog';
import { InputText } from 'primereact/inputtext';
import { InputTextarea } from 'primereact/inputtextarea';

import { TriStateCheckbox } from 'primereact/tristatecheckbox';
import { createChannelForCategoryPage, getAllChanellsNamesAndIdDTO } from 'app/entities/chanell/chanell.reducer';
import { NavLink } from 'reactstrap';
import { ICity } from 'app/shared/model/city.model';
import { IManager } from 'app/shared/model/manager.model';
import { Calendar } from 'primereact/calendar';
import {
  createRelCategoryCityChannel,
  getCategoryCityChannelsByRelCityCategory,
  partialUpdateRel,
} from 'app/entities/rel-category-city-channels/rel-category-city-channels.reducer';
import { getInfoAboutRelCategoryAndCity } from 'app/entities/rel-category-city/rel-category-city.reducer';
import { Dropdown } from 'primereact/dropdown';

// Primereact
// Primereact
// Primereact
export interface IAllRelChannelsCityCategoryProps
  extends StateProps,
    DispatchProps,
    RouteComponentProps<{
      idCity: string;
      id: string;
      idCat: string;
      relCategoryCityId: string;
    }> {}

export const AllRelChannelsCityCategory = (props: IAllRelChannelsCityCategoryProps) => {
  const [channel, setChannel] = useState(null);
  const [relCategoryCity, setRelCategoryCity] = useState(null);
  const [editChannelDialog, setEditChannelDialog] = useState(false);
  const [rel, setRel] = useState(null);
  const [isShowChannelState, setIsShowChannelState] = useState(false);
  const [addChanDialog, setAddChanDialog] = useState(false);
  const [channelsNames, setChannelsNames] = useState<any>(null);

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
    props.getCategoryCityChannelsByRelCityCategory(props.match.params.relCategoryCityId);
    props.getInfoAboutRelCategoryAndCity(props.match.params.relCategoryCityId).then(cat => {
      setRelCategoryCity(cat.value.data);
      window.console.log(cat.value.data, 'cat');
    });
  }, []);

  const { chanellList, match, loading, relCategoryCityChannels, channels } = props;

  const getAllChannelsNames = () => {
    props.getAllChanellsNamesAndIdDTO();
  };

  const addRelFunc = () => {
    setAddChanDialog(true);
    getAllChannelsNames();
  };

  const addChannel = (e: { value: any }) => {
    setChannelsNames(e.value);
    window.console.log(e.value, 'e.value');
  };

  const editChannel = rowData => {
    window.console.log(rowData, 'rowData-rowData');
    setRel(rowData);
    setIsShowChannelState(rowData?.isShowChannel);
    setEditChannelDialog(true);
  };

  const hideDialog = () => {
    setEditChannelDialog(false);
    setRel(null);
    commentValue.current.value = null;
    scoreValue.current.value = null;
    setIsShowChannelState(null);
  };

  const editRelButton = () => {
    const entity = {
      id: rel.id,
      scoreChannel: scoreValue.current.value,
      isShowChannel: isShowChannelState,
      comment: commentValue.current.value,
      idRel: props.match.params.relCategoryCityId,
    };

    props.partialUpdateRel(entity);

    setRel(null);
    commentValue.current.value = null;
    scoreValue.current.value = null;
    setIsShowChannelState(null);
    setEditChannelDialog(false);
  };

  const editChannelDialogFooter = (
    <React.Fragment>
      <Button label="Cancel" icon="pi pi-times" className="p-button-text" onClick={hideDialog} />
      <Button label="Save" icon="pi pi-check" className="p-button-text" onClick={editRelButton} />
    </React.Fragment>
  );

  const hideCreateDialog = () => {
    setRel(null);
    setIsShowChannelState(null);
    commentValue.current.value = null;
    scoreValue.current.value = null;
    setAddChanDialog(false);
  };

  const createRelButton = () => {
    const entity = {
      comment: commentValue.current.value,
      scoreChannel: scoreValue.current.value,
      isShowChannel: isShowChannelState,
      idRel: props.match.params.relCategoryCityId,
      idChannel: channelsNames?.id,
    };

    props.createRelCategoryCityChannel(entity);

    window.console.log(entity, 'entity');
    setChannelsNames(null);
    setIsShowChannelState(null);
    commentValue.current.value = null;
    scoreValue.current.value = null;
    setAddChanDialog(false);
  };

  const createRelDialogFooter = (
    <React.Fragment>
      <Button label="Cancel" icon="pi pi-times" className="p-button-text" onClick={hideCreateDialog} />
      <Button label="Save" icon="pi pi-check" className="p-button-text" onClick={createRelButton} />
    </React.Fragment>
  );

  const actionBodyTemplate = rowData => {
    return (
      <React.Fragment>
        <Button icon="pi pi-pencil" className="p-button-rounded p-button-success p-mr-2" onClick={() => editChannel(rowData)} />
      </React.Fragment>
    );
  };

  const selectedCountryTemplate = (option: { name: string; id: number }, innerProps: { placeholder: string }) => {
    if (option) {
      return (
        <div className="country-item country-item-value">
          <div>
            {option?.id} {option?.name}
          </div>
        </div>
      );
    }
    return <span> {innerProps.placeholder} </span>;
  };

  const countryOptionTemplate = (option: any) => {
    return (
      <div className="country-item">
        <div>
          {option?.id} {option?.name}
        </div>
      </div>
    );
  };

  return (
    <div>
      <div style={{ display: 'flex', alignItems: 'center' }}>
        <div style={{ flexGrow: 1, textAlign: 'center', fontSize: '1.4rem' }}>
          {relCategoryCity?.nameCity} - Каналы категории: {relCategoryCity?.nameCategory}
        </div>
        <div>
          <Button id="button_basic" label="Добавить канал" onClick={addRelFunc} />
        </div>
      </div>

      <br />
      <DataTable value={relCategoryCityChannels as any[]} sortMode="multiple" className="oi-p-datatable">
        <Column headerStyle={{ width: '5rem' }} field="id" header="№"></Column>
        <Column field="chanell.name" filter filterPlaceholder="Поиск по наименованию" sortable header="Имя канала"></Column>
        <Column
          field="link"
          style={{ maxWidth: '15vw', maxHight: '5vw' }}
          header="Ссылка"
          body={rowData => (
            <NavLink href={rowData?.link} target="_blank">
              {rowData?.chanell?.link}
            </NavLink>
          )}
        ></Column>
        <Column
          field="chanell.isModerate"
          sortable
          header="isModerate"
          style={{ width: '2vw' }}
          body={rowData => (rowData?.chanell?.isModerate !== null ? (rowData?.chanell?.isModerate ? 'Да' : 'Нет') : 'null')}
        ></Column>
        <Column
          field="scoreChannel"
          sortable
          header="Порядковый номер"
          style={{ width: '7.5vw' }}
          body={rowData => (rowData?.scoreChannel !== null ? rowData?.scoreChannel : 'null')}
        ></Column>
        <Column
          field="isShowChannel"
          sortable
          header="isShowChannel"
          style={{ width: '2vw' }}
          body={rowData => (rowData?.isShowChannel !== null ? (rowData?.isShowChannel ? 'Да' : 'Нет') : 'null')}
        ></Column>
        <Column
          field="chanell.endPublicDate"
          header="Конечная дата"
          sortable
          dataType="date"
          body={rowData => new Date(rowData?.chanell?.endPublicDate).toLocaleDateString()}
        ></Column>
        <Column
          field="chanell.isPay"
          sortable
          header="Размещение"
          style={{ width: '2vw' }}
          body={rowData => (rowData?.chanell?.isPay !== null ? (rowData?.chanell?.isPay ? 'Да' : 'Нет') : 'null')}
        ></Column>
        <Column field="comment" header="Комментарий" body={rowData => rowData?.comment}></Column>

        <Column body={actionBodyTemplate} exportable={false} style={{ minWidth: '8rem' }}></Column>
      </DataTable>

      <Dialog
        visible={editChannelDialog}
        style={{ width: '600px' }}
        header={'Канал - ' + rel?.chanell?.name}
        modal
        className="p-fluid"
        footer={editChannelDialogFooter}
        onHide={hideDialog}
        dismissableMask={true}
      >
        <br />

        <div className="p-field">
          <div> Порядковый номер </div>
          <InputText defaultValue={rel?.scoreChannel || ''} placeholder={'Введите порядковый номер'} ref={scoreValue} />
        </div>

        <br />

        <div className="card">
          Отображать - isShowChannel ?
          <div className="p-field-checkbox p-m-0">
            <TriStateCheckbox value={isShowChannelState} onChange={e => setIsShowChannelState(e.value)} />
            <div>{String(isShowChannelState)}</div>
          </div>
        </div>

        <div className="p-field">
          <div> Комментарий </div>
          <InputTextarea defaultValue={rel?.comment} rows={2} cols={20} ref={commentValue} />
        </div>

        <br />
      </Dialog>

      <Dialog
        visible={addChanDialog}
        style={{ width: '600px' }}
        header={relCategoryCity?.nameCity + ' - Добавление канала в категории: ' + relCategoryCity?.nameCategory}
        modal
        className="p-fluid"
        footer={createRelDialogFooter}
        onHide={hideCreateDialog}
        dismissableMask={true}
      >
        <br />
        <div className="p-field">
          <div> Выбрать канал </div>
          <Dropdown
            value={channelsNames}
            options={channels as any}
            onChange={addChannel}
            optionLabel="name"
            placeholder="Выбрать канал"
            filter
            showClear
            filterBy="name"
            valueTemplate={selectedCountryTemplate}
            itemTemplate={countryOptionTemplate}
          />
        </div>

        <br />

        <div className="p-field">
          <div> Порядковый номер </div>
          <InputText defaultValue={rel?.scoreChannel || ''} placeholder={'Введите порядковый номер'} ref={scoreValue} />
        </div>

        <br />

        <div className="card">
          Отображать - isShowChannel ?
          <div className="p-field-checkbox p-m-0">
            <TriStateCheckbox value={isShowChannelState} onChange={e => setIsShowChannelState(e.value)} />
            <div>{String(isShowChannelState)}</div>
          </div>
        </div>

        <div className="p-field">
          <div> Комментарий </div>
          <InputTextarea rows={2} cols={20} ref={commentValue} />
        </div>

        <br />
      </Dialog>
    </div>
  );
};

const mapStateToProps = ({ chanell, relCategoryCityChannels }: IRootState) => ({
  chanellList: chanell.entities,
  loading: chanell.loading,
  relCategoryCityChannels: relCategoryCityChannels.entities,
  channels: chanell.entities,
});

const mapDispatchToProps = {
  getInfoAboutRelCategoryAndCity,
  getCategoryCityChannelsByRelCityCategory,
  partialUpdateRel,
  getAllChanellsNamesAndIdDTO,
  createRelCategoryCityChannel,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AllRelChannelsCityCategory);
