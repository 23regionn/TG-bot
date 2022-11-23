import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './offer-from-costumers-log.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IOfferFromCostumersLogDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const OfferFromCostumersLogDetail = (props: IOfferFromCostumersLogDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { offerFromCostumersLogEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="offerFromCostumersLogDetailsHeading">OfferFromCostumersLog</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{offerFromCostumersLogEntity.id}</dd>
          <dt>
            <span id="text">Text</span>
            <UncontrolledTooltip target="text">текст предложения, обратная связь</UncontrolledTooltip>
          </dt>
          <dd>{offerFromCostumersLogEntity.text}</dd>
          <dt>
            <span id="isDelete">Is Delete</span>
            <UncontrolledTooltip target="isDelete">удаленный</UncontrolledTooltip>
          </dt>
          <dd>{offerFromCostumersLogEntity.isDelete ? 'true' : 'false'}</dd>
          <dt>
            <span id="adminId">Admin Id</span>
            <UncontrolledTooltip target="adminId">ссылка на админа, который пропустил отзыв</UncontrolledTooltip>
          </dt>
          <dd>{offerFromCostumersLogEntity.adminId}</dd>
          <dt>
            <span id="isActive">Is Active</span>
            <UncontrolledTooltip target="isActive">предложение = обработали или нет</UncontrolledTooltip>
          </dt>
          <dd>{offerFromCostumersLogEntity.isActive ? 'true' : 'false'}</dd>
          <dt>
            <span id="date1">Date 1</span>
          </dt>
          <dd>
            {offerFromCostumersLogEntity.date1 ? (
              <TextFormat value={offerFromCostumersLogEntity.date1} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="date2">Date 2</span>
          </dt>
          <dd>
            {offerFromCostumersLogEntity.date2 ? (
              <TextFormat value={offerFromCostumersLogEntity.date2} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="long1">Long 1</span>
          </dt>
          <dd>{offerFromCostumersLogEntity.long1}</dd>
          <dt>
            <span id="string1">String 1</span>
          </dt>
          <dd>{offerFromCostumersLogEntity.string1}</dd>
          <dt>
            <span id="boolean1">Boolean 1</span>
          </dt>
          <dd>{offerFromCostumersLogEntity.boolean1 ? 'true' : 'false'}</dd>
          <dt>Offer From Costumers</dt>
          <dd>{offerFromCostumersLogEntity.offerFromCostumers ? offerFromCostumersLogEntity.offerFromCostumers.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/offer-from-costumers-log" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/offer-from-costumers-log/${offerFromCostumersLogEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ offerFromCostumersLog }: IRootState) => ({
  offerFromCostumersLogEntity: offerFromCostumersLog.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(OfferFromCostumersLogDetail);
