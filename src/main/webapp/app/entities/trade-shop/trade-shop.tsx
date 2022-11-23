import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './trade-shop.reducer';
import { ITradeShop } from 'app/shared/model/trade-shop.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITradeShopProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const TradeShop = (props: ITradeShopProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const handleSyncList = () => {
    props.getEntities();
  };

  const { tradeShopList, match, loading } = props;
  return (
    <div>
      <h2 id="trade-shop-heading" data-cy="TradeShopHeading">
        Trade Shops
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Trade Shop
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {tradeShopList && tradeShopList.length > 0 ? (
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
                <th />
              </tr>
            </thead>
            <tbody>
              {tradeShopList.map((tradeShop, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${tradeShop.id}`} color="link" size="sm">
                      {tradeShop.id}
                    </Button>
                  </td>
                  <td>{tradeShop.category}</td>
                  <td>{tradeShop.priceDiapozon}</td>
                  <td>{tradeShop.currentPrice}</td>
                  <td>{tradeShop.whiceLineFromAllCountLines}</td>
                  <td>{tradeShop.tgUserIdWinner}</td>
                  <td>{tradeShop.inWhatDateWillPostThisLinks}</td>
                  <td>
                    {tradeShop.dateFinishTorgs ? (
                      <TextFormat type="date" value={tradeShop.dateFinishTorgs} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{tradeShop.isDelete ? 'true' : 'false'}</td>
                  <td>{tradeShop.date1 ? <TextFormat type="date" value={tradeShop.date1} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{tradeShop.date2 ? <TextFormat type="date" value={tradeShop.date2} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{tradeShop.long1}</td>
                  <td>{tradeShop.string1}</td>
                  <td>{tradeShop.boolean1 ? 'true' : 'false'}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${tradeShop.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${tradeShop.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${tradeShop.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Trade Shops found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ tradeShop }: IRootState) => ({
  tradeShopList: tradeShop.entities,
  loading: tradeShop.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TradeShop);
