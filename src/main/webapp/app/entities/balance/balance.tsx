import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './balance.reducer';
import { IBalance } from 'app/shared/model/balance.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBalanceProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Balance = (props: IBalanceProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const handleSyncList = () => {
    props.getEntities();
  };

  const { balanceList, match, loading } = props;
  return (
    <div>
      <h2 id="balance-heading" data-cy="BalanceHeading">
        Balances
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Balance
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {balanceList && balanceList.length > 0 ? (
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
                <th />
              </tr>
            </thead>
            <tbody>
              {balanceList.map((balance, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${balance.id}`} color="link" size="sm">
                      {balance.id}
                    </Button>
                  </td>
                  <td>{balance.balace}</td>
                  <td>{balance.userId}</td>
                  <td>{balance.frostSum}</td>
                  <td>
                    {balance.dateLastAddBalance ? (
                      <TextFormat type="date" value={balance.dateLastAddBalance} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    {balance.dateLastMinusFromBalance ? (
                      <TextFormat type="date" value={balance.dateLastMinusFromBalance} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{balance.date1 ? <TextFormat type="date" value={balance.date1} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{balance.date2 ? <TextFormat type="date" value={balance.date2} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{balance.long1}</td>
                  <td>{balance.string1}</td>
                  <td>{balance.boolean1 ? 'true' : 'false'}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${balance.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${balance.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${balance.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Balances found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ balance }: IRootState) => ({
  balanceList: balance.entities,
  loading: balance.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Balance);
