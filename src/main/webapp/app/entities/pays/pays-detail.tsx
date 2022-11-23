import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './pays.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPaysDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const PaysDetail = (props: IPaysDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { paysEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="paysDetailsHeading">Pays</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{paysEntity.id}</dd>
          <dt>
            <span id="datePaysSubscriptions">Date Pays Subscriptions</span>
            <UncontrolledTooltip target="datePaysSubscriptions">дата оплаты подписки</UncontrolledTooltip>
          </dt>
          <dd>
            {paysEntity.datePaysSubscriptions ? (
              <TextFormat value={paysEntity.datePaysSubscriptions} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="link">Link</span>
            <UncontrolledTooltip target="link">за какой канал он оплатил, за ссылку на какой канал</UncontrolledTooltip>
          </dt>
          <dd>{paysEntity.link}</dd>
          <dt>
            <span id="sumForPays">Sum For Pays</span>
            <UncontrolledTooltip target="sumForPays">сумма оплаты</UncontrolledTooltip>
          </dt>
          <dd>{paysEntity.sumForPays}</dd>
          <dt>
            <span id="typeBuy">Type Buy</span>
            <UncontrolledTooltip target="typeBuy">тип покупки</UncontrolledTooltip>
          </dt>
          <dd>{paysEntity.typeBuy}</dd>
          <dt>
            <span id="category">Category</span>
            <UncontrolledTooltip target="category">категория</UncontrolledTooltip>
          </dt>
          <dd>{paysEntity.category}</dd>
          <dt>
            <span id="typeBuyLong">Type Buy Long</span>
            <UncontrolledTooltip target="typeBuyLong">тип покупки long</UncontrolledTooltip>
          </dt>
          <dd>{paysEntity.typeBuyLong}</dd>
          <dt>
            <span id="date1">Date 1</span>
          </dt>
          <dd>{paysEntity.date1 ? <TextFormat value={paysEntity.date1} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="date2">Date 2</span>
          </dt>
          <dd>{paysEntity.date2 ? <TextFormat value={paysEntity.date2} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="long1">Long 1</span>
          </dt>
          <dd>{paysEntity.long1}</dd>
          <dt>
            <span id="string1">String 1</span>
          </dt>
          <dd>{paysEntity.string1}</dd>
          <dt>
            <span id="boolean1">Boolean 1</span>
          </dt>
          <dd>{paysEntity.boolean1 ? 'true' : 'false'}</dd>
          <dt>T G User</dt>
          <dd>{paysEntity.tGUser ? paysEntity.tGUser.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/pays" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/pays/${paysEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ pays }: IRootState) => ({
  paysEntity: pays.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(PaysDetail);
