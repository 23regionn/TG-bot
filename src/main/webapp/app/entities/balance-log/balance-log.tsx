import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './balance-log.reducer';
import { IBalanceLog } from 'app/shared/model/balance-log.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBalanceLogProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const BalanceLog = (props: IBalanceLogProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const handleSyncList = () => {
    props.getEntities();
  };

  const { balanceLogList, match, loading } = props;
  return (
    <div>
      <h2 id="balance-log-heading" data-cy="BalanceLogHeading">
        Balance Logs
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Balance Log
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {balanceLogList && balanceLogList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Balace</th>
                <th>User Id</th>
                <th>Frost Sum</th>
                <th>Date Last Add Balance</th>
                <th>Date Last Minus From Balance</th>
                <th>Date 1</th>
                <th>Date 2</th>
                <th>Long 1</th>
                <th>String 1</th>
                <th>Boolean 1</th>
                <th>Balance</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {balanceLogList.map((balanceLog, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${balanceLog.id}`} color="link" size="sm">
                      {balanceLog.id}
                    </Button>
                  </td>
                  <td>{balanceLog.balace}</td>
                  <td>{balanceLog.userId}</td>
                  <td>{balanceLog.frostSum}</td>
                  <td>
                    {balanceLog.dateLastAddBalance ? (
                      <TextFormat type="date" value={balanceLog.dateLastAddBalance} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    {balanceLog.dateLastMinusFromBalance ? (
                      <TextFormat type="date" value={balanceLog.dateLastMinusFromBalance} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{balanceLog.date1 ? <TextFormat type="date" value={balanceLog.date1} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{balanceLog.date2 ? <TextFormat type="date" value={balanceLog.date2} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{balanceLog.long1}</td>
                  <td>{balanceLog.string1}</td>
                  <td>{balanceLog.boolean1 ? 'true' : 'false'}</td>
                  <td>{balanceLog.balance ? <Link to={`balance/${balanceLog.balance.id}`}>{balanceLog.balance.id}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${balanceLog.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${balanceLog.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${balanceLog.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Balance Logs found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ balanceLog }: IRootState) => ({
  balanceLogList: balanceLog.entities,
  loading: balanceLog.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BalanceLog);
