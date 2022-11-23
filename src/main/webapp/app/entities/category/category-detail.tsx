import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './category.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICategoryDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CategoryDetail = (props: ICategoryDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { categoryEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="categoryDetailsHeading">Category</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{categoryEntity.id}</dd>
          <dt>
            <span id="name">Name</span>
          </dt>
          <dd>{categoryEntity.name}</dd>
          <dt>
            <span id="countChanellInCategory">Count Chanell In Category</span>
          </dt>
          <dd>{categoryEntity.countChanellInCategory}</dd>
          <dt>
            <span id="isDelete">Is Delete</span>
            <UncontrolledTooltip target="isDelete">удаленный</UncontrolledTooltip>
          </dt>
          <dd>{categoryEntity.isDelete ? 'true' : 'false'}</dd>
          <dt>
            <span id="date1">Date 1</span>
          </dt>
          <dd>{categoryEntity.date1 ? <TextFormat value={categoryEntity.date1} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="date2">Date 2</span>
          </dt>
          <dd>{categoryEntity.date2 ? <TextFormat value={categoryEntity.date2} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="long1">Long 1</span>
          </dt>
          <dd>{categoryEntity.long1}</dd>
          <dt>
            <span id="string1">String 1</span>
          </dt>
          <dd>{categoryEntity.string1}</dd>
          <dt>
            <span id="boolean1">Boolean 1</span>
          </dt>
          <dd>{categoryEntity.boolean1 ? 'true' : 'false'}</dd>
          <dt>Chanell Id</dt>
          <dd>
            {categoryEntity.chanellIds
              ? categoryEntity.chanellIds.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {categoryEntity.chanellIds && i === categoryEntity.chanellIds.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
          <dt>Links By Category In Top Id</dt>
          <dd>
            {categoryEntity.linksByCategoryInTopIds
              ? categoryEntity.linksByCategoryInTopIds.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {categoryEntity.linksByCategoryInTopIds && i === categoryEntity.linksByCategoryInTopIds.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/category" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/category/${categoryEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ category }: IRootState) => ({
  categoryEntity: category.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CategoryDetail);
