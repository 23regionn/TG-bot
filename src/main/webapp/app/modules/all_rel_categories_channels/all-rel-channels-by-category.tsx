import React, { useState, useEffect, useRef } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';

import { IRootState } from 'app/shared/reducers';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Button } from 'primereact/button';
import { Dialog } from 'primereact/dialog';
import { InputText } from 'primereact/inputtext';
import { TriStateCheckbox } from 'primereact/tristatecheckbox';
import { getAllChanellsNamesAndIdDTO } from 'app/entities/chanell/chanell.reducer';
import { NavLink } from 'reactstrap';
import {
  createRelCategoryChannel,
  getEntitiesByCategoryId,
  partialUpdateRel as partialUpdateRel,
} from 'app/entities/rel-category-channels/rel-category-channels.reducer';
import { getEntityCategoryById as getCategoryById } from 'app/modules/all-categories/all-categories.reducer';
import { Dropdown } from 'primereact/dropdown';
import { InputTextarea } from 'primereact/inputtextarea';

export interface IAllRelChannelsByCategoryProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AllRelChannelsByCategory = (props: IAllRelChannelsByCategoryProps) => {
  const [category, setCategory] = useState(null);
  const [rel, setRel] = useState(null);
  const [editChannelDialog, setEditChannelDialog] = useState(false);
  const [addChanDialog, setAddChanDialog] = useState(false);
  const scoreValue = useRef(null);
  const [isShowChannelState, setIsShowChannelState] = useState(false);
  const [channelsNames, setChannelsNames] = useState<any>(null);
  const commentValue = useRef(null);

  const { relCategoryChannels, match, loading, channels } = props;

  useEffect(() => {
    props.getEntitiesByCategoryId(props.match.params.id);
    props.getCategoryById(props.match.params.id).then(cat => {
      setCategory(cat.value.data);
    });
  }, []);

  const addRelFunc = () => {
    setAddChanDialog(true);
    getAllChannelsNames();
  };

  const editChannel = rowData => {
    window.console.log(rowData, 'rowData-rowData');
    setRel(rowData);
    setIsShowChannelState(rowData?.isShowChannel);
    setEditChannelDialog(true);
  };

  const actionBodyTemplate = rowData => {
    return (
      <React.Fragment>
        <Button icon="pi pi-pencil" className="p-button-rounded p-button-success p-mr-2" onClick={() => editChannel(rowData)} />
      </React.Fragment>
    );
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
      idCat: props.match.params.id,
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

  const addChannel = (e: { value: any }) => {
    setChannelsNames(e.value);
    window.console.log(e.value, 'e.value');
  };

  const getAllChannelsNames = () => {
    props.getAllChanellsNamesAndIdDTO();
  };

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
      idCat: props.match.params.id,
      idChannel: channelsNames?.id,
    };

    props.createRelCategoryChannel(entity);

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

  const selectedCountryTemplate = (option: { name: string; id: number }, props: { placeholder: string }) => {
    if (option) {
      return (
        <div className="country-item country-item-value">
          <div>
            {option?.id} {option?.name}
          </div>
        </div>
      );
    }
    return <span> {props.placeholder} </span>;
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
        <div style={{ flexGrow: 1, textAlign: 'center', fontSize: '1.4rem' }}>Каналы категории: {category?.name}</div>
        <div>
          <Button id="button_basic" label="Добавить канал" onClick={addRelFunc} />
        </div>
      </div>

      <br />
      <DataTable value={relCategoryChannels as any[]} sortMode="multiple" className="oi-p-datatable">
        <Column headerStyle={{ width: '5rem' }} field="id" header="№"></Column>
        {/*<Column field="name" body={rowData => ( rowData?.chanell?.name)} filter filterPlaceholder="Поиск по наименованию" sortable header="Имя канала"></Column>*/}
        <Column field="chanell.name" filter filterPlaceholder="Поиск по наименованию" sortable header="Имя канала"></Column>
        <Column
          field="link"
          style={{ maxWidth: '15vw', maxHight: '5vw' }}
          header="Ссылка"
          body={rowData => (
            <NavLink href={rowData?.chanell?.link} target="_blank">
              {rowData?.chanell?.link}
            </NavLink>
          )}
        ></Column>
        <Column
          field="isShowChannel"
          sortable
          header="isShowChannel"
          body={rowData => (rowData.isShowChannel !== null ? (rowData.isShowChannel ? 'Да' : 'Нет') : null)}
        ></Column>
        <Column
          field="scoreChannel"
          sortable
          header="Порядковый номер"
          style={{ width: '7.5vw' }}
          body={rowData => (rowData.scoreChannel !== null ? rowData.scoreChannel : 'null')}
        ></Column>
        <Column style={{ maxWidth: '15vw' }} field="comment" header="Комментарий" body={rowData => rowData?.comment}></Column>

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
        header={'Добавление канала в категории: ' + category?.name}
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

const mapStateToProps = ({ relCategoryChannels, chanell }: IRootState) => ({
  relCategoryChannels: relCategoryChannels.entities,
  loading: relCategoryChannels.loading,
  channels: chanell.entities,
});

const mapDispatchToProps = {
  getEntitiesByCategoryId,
  getCategoryById,
  partialUpdateRel,
  getAllChanellsNamesAndIdDTO,
  createRelCategoryChannel,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AllRelChannelsByCategory);
