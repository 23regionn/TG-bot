import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './trade-shop-log.reducer';
import { ITradeShopLog } from 'app/shared/model/trade-shop-log.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITradeShopLogProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const TradeShopLog = (props: ITradeShopLogProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const handleSyncList = () => {
    props.getEntities();
  };

  const { tradeShopLogList, match, loading } = props;
  return (
    <div>
      <h2 id="trade-shop-log-heading" data-cy="TradeShopLogHeading">
        Trade Shop Logs
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Trade Shop Log
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {tradeShopLogList && tradeShopLogList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Category</th>
                <th>Price Diapozon</th>
                <th>Current Price</th>
                <th>Whice Line From All Count Lines</th>
                <th>Tg User Id Winner</th>
                <th>In What Date Will Post This Links</th>
                <th>Date Finish Torgs</th>
                <th>Is Delete</th>
                <th>Date 1</th>
                <th>Date 2</th>
                <th>Long 1</th>
                <th>String 1</th>
                <th>Boolean 1</th>
                <th>Trade Shop</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {tradeShopLogList.map((tradeShopLog, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${tradeShopLog.id}`} color="link" size="sm">
                      {tradeShopLog.id}
                    </Button>
                  </td>
                  <td>{tradeShopLog.category}</td>
                  <td>{tradeShopLog.priceDiapozon}</td>
                  <td>{tradeShopLog.currentPrice}</td>
                  <td>{tradeShopLog.whiceLineFromAllCountLines}</td>
                  <td>{tradeShopLog.tgUserIdWinner}</td>
                  <td>{tradeShopLog.inWhatDateWillPostThisLinks}</td>
                  <td>
                    {tradeShopLog.dateFinishTorgs ? (
                      <TextFormat type="date" value={tradeShopLog.dateFinishTorgs} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{tradeShopLog.isDelete ? 'true' : 'false'}</td>
                  <td>{tradeShopLog.date1 ? <TextFormat type="date" value={tradeShopLog.date1} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{tradeShopLog.date2 ? <TextFormat type="date" value={tradeShopLog.date2} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{tradeShopLog.long1}</td>
                  <td>{tradeShopLog.string1}</td>
                  <td>{tradeShopLog.boolean1 ? 'true' : 'false'}</td>
                  <td>
                    {tradeShopLog.tradeShop ? <Link to={`trade-shop/${tradeShopLog.tradeShop.id}`}>{tradeShopLog.tradeShop.id}</Link> : ''}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${tradeShopLog.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${tradeShopLog.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${tradeShopLog.id}/delete`}
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
          !loading && <div className="alert alert-warning">No Trade Shop Logs found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ tradeShopLog }: IRootState) => ({
  tradeShopLogList: tradeShopLog.entities,
  loading: tradeShopLog.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TradeShopLog);
