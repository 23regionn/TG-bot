import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './edit-channels.reducer';
import { IEditChannels } from 'app/shared/model/edit-channels.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEditChannelsProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const EditChannels = (props: IEditChannelsProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const handleSyncList = () => {
    props.getEntities();
  };

  const { editChannelsList, match, loading } = props;
  return (
    <div>
      <h2 id="edit-channels-heading" data-cy="EditChannelsHeading">
        Edit Channels
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Edit Channels
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {editChannelsList && editChannelsList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Id Message</th>
                <th>Id Channel</th>
                <th>Date Create Message</th>
                <th>Last Name Channel</th>
                <th>New Name Channel</th>
                <th>Is Aprove Change</th>
                <th>Last Link To Channel</th>
                <th>Newlast Link To Channel</th>
                <th>Last Price Channel</th>
                <th>New Price Channel</th>
                <th>Add Description About Channel</th>
                <th>Current Description Channel</th>
                <th>Add Region Channel</th>
                <th>Edit Region Channel</th>
                <th>Add City Channel</th>
                <th>Edit City Channel</th>
                <th>User Id</th>
                <th>User Name</th>
                <th>Is Approved Chanhes</th>
                <th>Comment</th>
                <th>Status</th>
                <th>Service Field 1</th>
                <th>Service Field 2</th>
                <th>Service Field 3</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {editChannelsList.map((editChannels, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${editChannels.id}`} color="link" size="sm">
                      {editChannels.id}
                    </Button>
                  </td>
                  <td>{editChannels.idMessage}</td>
                  <td>{editChannels.idChannel}</td>
                  <td>
                    {editChannels.dateCreateMessage ? (
                      <TextFormat type="date" value={editChannels.dateCreateMessage} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{editChannels.lastNameChannel}</td>
                  <td>{editChannels.newNameChannel}</td>
                  <td>{editChannels.isAproveChange}</td>
                  <td>{editChannels.lastLinkToChannel}</td>
                  <td>{editChannels.newlastLinkToChannel}</td>
                  <td>{editChannels.lastPriceChannel}</td>
                  <td>{editChannels.newPriceChannel}</td>
                  <td>{editChannels.addDescriptionAboutChannel}</td>
                  <td>{editChannels.currentDescriptionChannel}</td>
                  <td>{editChannels.addRegionChannel}</td>
                  <td>{editChannels.editRegionChannel}</td>
                  <td>{editChannels.addCityChannel}</td>
                  <td>{editChannels.editCityChannel}</td>
                  <td>{editChannels.userId}</td>
                  <td>{editChannels.userName}</td>
                  <td>{editChannels.isApprovedChanhes ? 'true' : 'false'}</td>
                  <td>{editChannels.comment}</td>
                  <td>{editChannels.status}</td>
                  <td>{editChannels.serviceField1}</td>
                  <td>{editChannels.serviceField2}</td>
                  <td>{editChannels.serviceField3}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${editChannels.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${editChannels.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${editChannels.id}/delete`}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Edit Channels found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ editChannels }: IRootState) => ({
  editChannelsList: editChannels.entities,
  loading: editChannels.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(EditChannels);
