import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './show-channels-in-city-log.reducer';
import { IShowChannelsInCityLog } from 'app/shared/model/show-channels-in-city-log.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IShowChannelsInCityLogProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const ShowChannelsInCityLog = (props: IShowChannelsInCityLogProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const handleSyncList = () => {
    props.getEntities();
  };

  const { showChannelsInCityLogList, match, loading } = props;
  return (
    <div>
      <h2 id="show-channels-in-city-log-heading" data-cy="ShowChannelsInCityLogHeading">
        Show Channels In City Logs
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Show Channels In City Log
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {showChannelsInCityLogList && showChannelsInCityLogList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Id Channel</th>
                <th>Name Channel</th>
                <th>Id Category</th>
                <th>Name Category</th>
                <th>Id City</th>
                <th>Name City</th>
                <th>Is Show Channel</th>
                <th>Score Channel</th>
                <th>Comment</th>
                <th>Date Log</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {showChannelsInCityLogList.map((showChannelsInCityLog, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${showChannelsInCityLog.id}`} color="link" size="sm">
                      {showChannelsInCityLog.id}
                    </Button>
                  </td>
                  <td>{showChannelsInCityLog.idChannel}</td>
                  <td>{showChannelsInCityLog.nameChannel}</td>
                  <td>{showChannelsInCityLog.idCategory}</td>
                  <td>{showChannelsInCityLog.nameCategory}</td>
                  <td>{showChannelsInCityLog.idCity}</td>
                  <td>{showChannelsInCityLog.nameCity}</td>
                  <td>{showChannelsInCityLog.isShowChannel ? 'true' : 'false'}</td>
                  <td>{showChannelsInCityLog.scoreChannel}</td>
                  <td>{showChannelsInCityLog.comment}</td>
                  <td>
                    {showChannelsInCityLog.dateLog ? (
                      <TextFormat type="date" value={showChannelsInCityLog.dateLog} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`${match.url}/${showChannelsInCityLog.id}`}
                        color="info"
                        size="sm"
                        data-cy="entityDetailsButton"
                      >
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${showChannelsInCityLog.id}/edit`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${showChannelsInCityLog.id}/delete`}
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
          !loading && <div className="alert alert-warning">No Show Channels In City Logs found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ showChannelsInCityLog }: IRootState) => ({
  showChannelsInCityLogList: showChannelsInCityLog.entities,
  loading: showChannelsInCityLog.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ShowChannelsInCityLog);
