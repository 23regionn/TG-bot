import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './chanell.reducer';
import { IChanell } from 'app/shared/model/chanell.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IChanellProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Chanell = (props: IChanellProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const handleSyncList = () => {
    props.getEntities();
  };

  const { chanellList, match, loading } = props;
  return (
    <div>
      <h2 id="chanell-heading" data-cy="ChanellHeading">
        Chanells
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Chanell
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {chanellList && chanellList.length > 0 ? (
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
                <th>T G User</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {chanellList.map((chanell, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${chanell.id}`} color="link" size="sm">
                      {chanell.id}
                    </Button>
                  </td>
                  <td>{chanell.name}</td>
                  <td>{chanell.link}</td>
                  <td>{chanell.score}</td>
                  <td>{chanell.status}</td>
                  <td>{chanell.countSubscribers}</td>
                  <td>{chanell.quailityFromAnotherSources}</td>
                  <td>{chanell.priceDiapozon}</td>
                  <td>{chanell.isModerate ? 'true' : 'false'}</td>
                  <td>{chanell.showChanellInTopByCategory ? 'true' : 'false'}</td>
                  <td>{chanell.region}</td>
                  <td>{chanell.city}</td>
                  <td>{chanell.isDelete ? 'true' : 'false'}</td>
                  <td>{chanell.currentDate ? <TextFormat type="date" value={chanell.currentDate} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{chanell.date1 ? <TextFormat type="date" value={chanell.date1} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{chanell.date2 ? <TextFormat type="date" value={chanell.date2} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{chanell.long1}</td>
                  <td>{chanell.string1}</td>
                  <td>{chanell.boolean1 ? 'true' : 'false'}</td>
                  <td>{chanell.tGUser ? <Link to={`tg-user/${chanell.tGUser.id}`}>{chanell.tGUser.id}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${chanell.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${chanell.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${chanell.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Chanells found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ chanell }: IRootState) => ({
  chanellList: chanell.entities,
  loading: chanell.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Chanell);
