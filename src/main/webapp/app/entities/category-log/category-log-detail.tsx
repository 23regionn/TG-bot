import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './category-log.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICategoryLogDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CategoryLogDetail = (props: ICategoryLogDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { categoryLogEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="categoryLogDetailsHeading">CategoryLog</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{categoryLogEntity.id}</dd>
          <dt>
            <span id="name">Name</span>
          </dt>
          <dd>{categoryLogEntity.name}</dd>
          <dt>
            <span id="countChanellInCategory">Count Chanell In Category</span>
          </dt>
          <dd>{categoryLogEntity.countChanellInCategory}</dd>
          <dt>
            <span id="isDelete">Is Delete</span>
            <UncontrolledTooltip target="isDelete">удаленный</UncontrolledTooltip>
          </dt>
          <dd>{categoryLogEntity.isDelete ? 'true' : 'false'}</dd>
          <dt>
            <span id="date1">Date 1</span>
          </dt>
          <dd>{categoryLogEntity.date1 ? <TextFormat value={categoryLogEntity.date1} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="date2">Date 2</span>
          </dt>
          <dd>{categoryLogEntity.date2 ? <TextFormat value={categoryLogEntity.date2} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="long1">Long 1</span>
          </dt>
          <dd>{categoryLogEntity.long1}</dd>
          <dt>
            <span id="string1">String 1</span>
          </dt>
          <dd>{categoryLogEntity.string1}</dd>
          <dt>
            <span id="boolean1">Boolean 1</span>
          </dt>
          <dd>{categoryLogEntity.boolean1 ? 'true' : 'false'}</dd>
          <dt>Category</dt>
          <dd>{categoryLogEntity.category ? categoryLogEntity.category.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/category-log" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/category-log/${categoryLogEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ categoryLog }: IRootState) => ({
  categoryLogEntity: categoryLog.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CategoryLogDetail);
