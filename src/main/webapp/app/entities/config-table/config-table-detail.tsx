import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './config-table.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IConfigTableDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ConfigTableDetail = (props: IConfigTableDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { configTableEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="configTableDetailsHeading">ConfigTable</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{configTableEntity.id}</dd>
          <dt>
            <span id="dateOne">Date One</span>
          </dt>
          <dd>
            {configTableEntity.dateOne ? <TextFormat value={configTableEntity.dateOne} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="dateTwo">Date Two</span>
          </dt>
          <dd>
            {configTableEntity.dateTwo ? <TextFormat value={configTableEntity.dateTwo} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="longOne">Long One</span>
          </dt>
          <dd>{configTableEntity.longOne}</dd>
          <dt>
            <span id="stringOne">String One</span>
          </dt>
          <dd>{configTableEntity.stringOne}</dd>
          <dt>
            <span id="booleanOne">Boolean One</span>
          </dt>
          <dd>{configTableEntity.booleanOne ? 'true' : 'false'}</dd>
          <dt>
            <span id="booleanTwo">Boolean Two</span>
          </dt>
          <dd>{configTableEntity.booleanTwo ? 'true' : 'false'}</dd>
          <dt>
            <span id="date1">Date 1</span>
          </dt>
          <dd>{configTableEntity.date1 ? <TextFormat value={configTableEntity.date1} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="date2">Date 2</span>
          </dt>
          <dd>{configTableEntity.date2 ? <TextFormat value={configTableEntity.date2} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="long1">Long 1</span>
          </dt>
          <dd>{configTableEntity.long1}</dd>
          <dt>
            <span id="string1">String 1</span>
          </dt>
          <dd>{configTableEntity.string1}</dd>
          <dt>
            <span id="boolean1">Boolean 1</span>
          </dt>
          <dd>{configTableEntity.boolean1 ? 'true' : 'false'}</dd>
        </dl>
        <Button tag={Link} to="/config-table" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/config-table/${configTableEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ configTable }: IRootState) => ({
  configTableEntity: configTable.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ConfigTableDetail);
