import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './chanell-log.reducer';
import { IChanellLog } from 'app/shared/model/chanell-log.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IChanellLogProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const ChanellLog = (props: IChanellLogProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const handleSyncList = () => {
    props.getEntities();
  };

  const { chanellLogList, match, loading } = props;
  return (
    <div>
      <h2 id="chanell-log-heading" data-cy="ChanellLogHeading">
        Chanell Logs
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Chanell Log
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {chanellLogList && chanellLogList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Link</th>
                <th>Score</th>
                <th>Status</th>
                <th>Count Subscribers</th>
                <th>Quaility From Another Sources</th>
                <th>Price Diapozon</th>
                <th>Is Moderate</th>
                <th>Show Chanell In Top By Category</th>
                <th>Region</th>
                <th>City</th>
                <th>Is Delete</th>
                <th>Current Date</th>
                <th>Date 1</th>
                <th>Date 2</th>
                <th>Long 1</th>
                <th>String 1</th>
                <th>Boolean 1</th>
                <th>Chanell</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {chanellLogList.map((chanellLog, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${chanellLog.id}`} color="link" size="sm">
                      {chanellLog.id}
                    </Button>
                  </td>
                  <td>{chanellLog.name}</td>
                  <td>{chanellLog.link}</td>
                  <td>{chanellLog.score}</td>
                  <td>{chanellLog.status}</td>
                  <td>{chanellLog.countSubscribers}</td>
                  <td>{chanellLog.quailityFromAnotherSources}</td>
                  <td>{chanellLog.priceDiapozon}</td>
                  <td>{chanellLog.isModerate ? 'true' : 'false'}</td>
                  <td>{chanellLog.showChanellInTopByCategory ? 'true' : 'false'}</td>
                  <td>{chanellLog.region}</td>
                  <td>{chanellLog.city}</td>
                  <td>{chanellLog.isDelete ? 'true' : 'false'}</td>
                  <td>
                    {chanellLog.currentDate ? <TextFormat type="date" value={chanellLog.currentDate} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{chanellLog.date1 ? <TextFormat type="date" value={chanellLog.date1} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{chanellLog.date2 ? <TextFormat type="date" value={chanellLog.date2} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{chanellLog.long1}</td>
                  <td>{chanellLog.string1}</td>
                  <td>{chanellLog.boolean1 ? 'true' : 'false'}</td>
                  <td>{chanellLog.chanell ? <Link to={`chanell/${chanellLog.chanell.id}`}>{chanellLog.chanell.id}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${chanellLog.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${chanellLog.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${chanellLog.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Chanell Logs found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ chanellLog }: IRootState) => ({
  chanellLogList: chanellLog.entities,
  loading: chanellLog.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ChanellLog);
