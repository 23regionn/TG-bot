import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './balance.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBalanceDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const BalanceDetail = (props: IBalanceDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { balanceEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="balanceDetailsHeading">Balance</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{balanceEntity.id}</dd>
          <dt>
            <span id="balace">Balace</span>
          </dt>
          <dd>{balanceEntity.balace}</dd>
          <dt>
            <span id="userId">User Id</span>
          </dt>
          <dd>{balanceEntity.userId}</dd>
          <dt>
            <span id="frostSum">Frost Sum</span>
            <UncontrolledTooltip target="frostSum">сумма заморозки для торгов</UncontrolledTooltip>
          </dt>
          <dd>{balanceEntity.frostSum}</dd>
          <dt>
            <span id="dateLastAddBalance">Date Last Add Balance</span>
          </dt>
          <dd>
            {balanceEntity.dateLastAddBalance ? (
              <TextFormat value={balanceEntity.dateLastAddBalance} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="dateLastMinusFromBalance">Date Last Minus From Balance</span>
          </dt>
          <dd>
            {balanceEntity.dateLastMinusFromBalance ? (
              <TextFormat value={balanceEntity.dateLastMinusFromBalance} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="date1">Date 1</span>
          </dt>
          <dd>{balanceEntity.date1 ? <TextFormat value={balanceEntity.date1} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="date2">Date 2</span>
          </dt>
          <dd>{balanceEntity.date2 ? <TextFormat value={balanceEntity.date2} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="long1">Long 1</span>
          </dt>
          <dd>{balanceEntity.long1}</dd>
          <dt>
            <span id="string1">String 1</span>
          </dt>
          <dd>{balanceEntity.string1}</dd>
          <dt>
            <span id="boolean1">Boolean 1</span>
          </dt>
          <dd>{balanceEntity.boolean1 ? 'true' : 'false'}</dd>
        </dl>
        <Button tag={Link} to="/balance" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/balance/${balanceEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ balance }: IRootState) => ({
  balanceEntity: balance.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BalanceDetail);
